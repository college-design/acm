package com.lxg.acm.core;

import com.lxg.acm.constant.JudgeResultType;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.concurrent.*;

/**
 *
 * @author Administrator
 */

public final class JudgeCore {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	String compileShell;  // 编译命令
	String executeShell;  // 执行命令
	String dataForNum;    // 题号
	int result;           // 结果
	int timeUsed;         // 编译时间
	int memory;           // 运行大小
	int COMPILETIMELIMIT = 1500;
	int TIMELIMIT = 3 * 1000;
	int MEMORYLIMIT = 64 * 1024;
	String errorInfo;     // 错误信息

	private static final ScheduledExecutorService schedule = Executors.newScheduledThreadPool(10);

	private static MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

	private static final String DATA_IN_FILE_EXT = ".in";  // 输入
	private static final String DATA_OUT_FILE_EXT = ".out"; // 输出

	public void run() {
		try {
			if (compile())
				execute();
		} catch (Exception e) {
			logger.error("编译异常",e);
			this.result = JudgeResultType.SE;
		}
	}

	public boolean compile() throws Exception {
		logger.info("编译开始={}",compileShell);

		final Process process = Runtime.getRuntime().exec(compileShell);
		ScheduledFuture<Integer> checkTimeFuture = schedule.schedule(
				new Callable<Integer>() {
					public Integer call() throws Exception {
						process.destroy();
						return JudgeResultType.CE;
					}
				}, COMPILETIMELIMIT, TimeUnit.MILLISECONDS);
		int wait = process.waitFor();
		int exit = process.exitValue();
		logger.info("编译等待时间wait={}，exit={}",wait,exit);
		if (checkTimeFuture.isDone()) {
			this.result = checkTimeFuture.get();
			InputStream is = process.getErrorStream();
			this.errorInfo = IOUtils.toString(is, "gbk");
			is.close();
			return false;
		}
		checkTimeFuture.cancel(true);
		if (wait == 0 && exit == 0) {
			// ok
			return true;
		} else {
			// ce
			InputStream is = process.getErrorStream();
			this.errorInfo = IOUtils.toString(is, "gbk");
			this.result = JudgeResultType.CE;
			is.close();
		}
		return false;
	}

	public void execute() throws Exception {
		logger.info("===========对比开始===========");
		File dataFile = new File(dataForNum);
		File[] dataOutFiles = getDataOutFiles(dataFile);
		for (int i = 0; i < dataOutFiles.length; i++) {
			File dataOutFile = dataOutFiles[i];
			File dataInfile = new File(dataForNum,
					FilenameUtils.getBaseName(dataOutFile.getAbsolutePath())
							+ DATA_IN_FILE_EXT);
			if (dataInfile.exists()) {
				// important!! clean memory.
				System.gc();
				long timeStarted = System.currentTimeMillis();
				long memoryStarted = memoryBean.getHeapMemoryUsage().getUsed();
				final Process process = Runtime.getRuntime().exec(executeShell);
				// kill process if timeout
				ScheduledFuture<Integer> checkTimeFuture = schedule.schedule(
						new Callable<Integer>() {
							public Integer call() throws Exception {
								process.destroy();
								return JudgeResultType.TLE;
							}
						}, TIMELIMIT / dataOutFiles.length,
						TimeUnit.MILLISECONDS);

				OutputStream pos = process.getOutputStream();
				InputStream is = new FileInputStream(dataInfile);
				IOUtils.copy(is, pos);
				pos.close();
				is.close();

				int wait = process.waitFor();
				int exit = process.exitValue();

				// get and canel Scheduled
				if (checkTimeFuture.isDone()) {
					this.result = checkTimeFuture.get();
					return;
				}
				checkTimeFuture.cancel(true);
				if (wait == 0 && exit == 0) {
					this.timeUsed += (int) (System.currentTimeMillis() - timeStarted);
					if (this.timeUsed > this.TIMELIMIT) {
						this.result = JudgeResultType.TLE;
						return;
					}

					this.memory += (int) (memoryBean.getHeapMemoryUsage()
							.getUsed() - memoryStarted) / 1024;
					if (this.memory > this.MEMORYLIMIT) {
						this.result = JudgeResultType.MLE;
						return;
					}

					InputStream dis = new FileInputStream(dataOutFile);
					InputStream pis = process.getInputStream();
					this.result = match(IOUtils.toByteArray(dis),IOUtils.toByteArray(pis));
					dis.close();
					pis.close();
					if (this.result == JudgeResultType.AC)
						continue;
					else {
						return;
					}
				} else {
					// runtime error
					InputStream pes = process.getErrorStream();
					this.errorInfo = IOUtils.toString(pes, "gbk");
					pes.close();
					this.result = JudgeResultType.RE;
					return;
				}
			} else {
				// no infile
				this.result = JudgeResultType.SE;
				return;
			}
		}
	}

	private File[] getDataOutFiles(File file) {
		return file.listFiles(new FilenameFilter() {
			public boolean accept(File file, String filename) {
				return filename.toLowerCase().endsWith(DATA_OUT_FILE_EXT);
			}
		});
	}

	int match(byte[] stdOutput, byte[] output) {
		boolean pe = false;
		int i = 0, pi = 0, j = 0, pj = 0;
		boolean bi = false, bj = false;
		while (i < stdOutput.length && j < output.length) {
			byte src = stdOutput[i];
			byte dest = output[j];
			while (i < stdOutput.length && (src == ' ' || src == '\t' || src == '\r' || src == '\n')) {
				i++;
				if (i < stdOutput.length)
					src = stdOutput[i];
				bi = true;
			}
			if (bi && (pi != 0)) {
				pi++;
				bi = false;
			}
			while (j < output.length && (dest == ' ' || dest == '\t' || dest == '\r' || dest == '\n')) {
				j++;
				if (j < output.length)
					dest = output[j];
				bj = true;
			}
			if (bj && (pj != 0)) {
				pj++;
				bj = false;
			}
			if (i < stdOutput.length && j < output.length) {
				if (src == dest) {
					++i;
					++j;
					pi++;
					pj++;
					continue;
				} else {
					return JudgeResultType.WA;
				}
			}
		}
		pe = (pi != pj);
		while (i < stdOutput.length) {
			byte src = stdOutput[i];
			if (!(src == ' ' || src == '\t' || src == '\r' || src == '\n')) {
				return JudgeResultType.WA;
			}
			++i;
		}
		while (j < output.length) {
			byte src = output[j];
			if (!(src == ' ' || src == '\t' || src == '\r' || src == '\n')) {
				return JudgeResultType.WA;
			}
			++j;
		}
		if (pe) {
			return JudgeResultType.PE;
		}
		return JudgeResultType.AC;
	}

	public int getResult() {
		return result;
	}

	public int getTimeUsed() {
		return timeUsed;
	}

	public int getMemory() {
		return memory;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setCompileShell(String compileShell) {
		this.compileShell = compileShell;
	}

	public void setExecuteShell(String executeShell) {
		this.executeShell = executeShell;
	}

	public void setDataForNum(String dataForNum) {
		this.dataForNum = dataForNum;
	}

	public void setTIMELIMIT(int tIMELIMIT) {
		TIMELIMIT = tIMELIMIT;
	}

	public void setMEMORYLIMIT(int mEMORYLIMIT) {
		MEMORYLIMIT = mEMORYLIMIT;
	}

}
