package com.lxg.acm.support;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lxg.acm.context.OJConfig;
import com.lxg.acm.context.OJConfig.Language;
import com.lxg.acm.core.JudgeCore;
import com.lxg.acm.entity.Problem;
import com.lxg.acm.entity.Status;
import com.lxg.acm.mapper.ProblemMapper;
import com.lxg.acm.mapper.StatusMapper;
import com.lxg.acm.mapper.UserMapper;
import com.lxg.acm.util.SpringUtil;


public class JudgeSupport extends Thread {
	private static final Log LOG = LogFactory.getLog(JudgeSupport.class);
	private static final ExecutorService executor = Executors
			.newCachedThreadPool();
	StatusMapper statusMapper = SpringUtil.getBean(StatusMapper.class);
	ProblemMapper problemMapper = SpringUtil.getBean(ProblemMapper.class);
	UserMapper userMapper = SpringUtil.getBean(UserMapper.class);

	Status status;
	String code;

	public JudgeSupport(Status status, String code) {
		this.status = status;
		this.code = code;
		executor.execute(this);
	}

	static SecureRandom random = new SecureRandom();

	private String randomFileName() {
		byte[] buff = new byte[8];
		random.nextBytes(buff);
		return new BigInteger(1, buff).toString(16);
	}

	public void run() {
		Language language = OJConfig.instance.langs.get(status.getLanguage());
		String folderName = randomFileName();
		File folder = new File(OJConfig.instance.tempFile, folderName);
		File mainFile = new File(folder, "Main." + language.ext);
		try {
			FileUtils.write(mainFile, code);
		} catch (IOException e) {
		}
		JudgeCore core = new JudgeCore();
		core.setDataForNum(OJConfig.instance.dataFile + File.separatorChar
				+ status.getPid());
		core.setCompileShell(language.getComshell(OJConfig.instance.tempFile
				+ File.separatorChar + folderName + File.separatorChar));
		core.setExecuteShell(language.getRunshell(OJConfig.instance.tempFile
				+ File.separatorChar + folderName + File.separatorChar));
		core.setMEMORYLIMIT(language.getMemorylimit());
		core.setTIMELIMIT(language.getTimelimit());
		core.run();

		// 题目AC/ALL
		Long pid = status.getPid();
		Problem problem= problemMapper.query(pid);
		// 判断题目是否通过
		if(0==core.getResult()){ // 通过
			problem.setAccepted(problem.getAccepted()+1);
			problem.setSubmit(problem.getSubmit()+1);
			problem.setRatio((problem.getAccepted()+0.0)/problem.getSubmit());
		}else{ // 未通过
			problem.setSubmit(problem.getSubmit()+1);
			problem.setRatio((problem.getAccepted()+0.0)/problem.getSubmit());
		}
		
		status.setMemory(core.getMemory());
		status.setResult(core.getResult());
		status.setTime(core.getTimeUsed());
		LOG.info("result:" + status.getResult() + "  memory:"
				+ status.getMemory() + "  time:" + status.getTime());
		LOG.info("error:" + core.getErrorInfo());
		statusMapper.update(status);   // 更新代码状态
		problemMapper.update(problem); // 更新题目提交率
		deleteFloder(folder);
	}

	private static final ScheduledExecutorService schedule = Executors
			.newScheduledThreadPool(0);

	private void deleteFloder(final File folder) {
		try {
			schedule.schedule(new Runnable() {
				public void run() {
					try {
						FileUtils.deleteDirectory(folder);
					} catch (IOException e) {
					}
				}
			}, 6, TimeUnit.SECONDS);
			FileUtils.deleteDirectory(folder);
		} catch (IOException e) {
		}
	}
}
