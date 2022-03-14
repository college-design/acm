package com.lxg.acm.support;

import com.alibaba.fastjson.JSON;
import com.lxg.acm.context.OJConfig;
import com.lxg.acm.context.OJConfig.Language;
import com.lxg.acm.core.JudgeCore;
import com.lxg.acm.entity.Problem;
import com.lxg.acm.entity.Status;
import com.lxg.acm.entity.User;
import com.lxg.acm.mapper.ProblemMapper;
import com.lxg.acm.mapper.StatusMapper;
import com.lxg.acm.mapper.UserMapper;
import com.lxg.acm.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JudgeSupport extends Thread {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final ExecutorService executor = Executors.newCachedThreadPool();

	private StatusMapper statusMapper = SpringUtil.getBean(StatusMapper.class);
	private ProblemMapper problemMapper = SpringUtil.getBean(ProblemMapper.class);
	private UserMapper userMapper = SpringUtil.getBean(UserMapper.class);

	private Status status;
	private String code;

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
		logger.info("=====代码编译开始start=====");
		Language language = OJConfig.instance.langs.get(status.getLanguage());
		logger.info("编译语言信息{}", JSON.toJSON(language));
		String folderName = randomFileName();
		logger.info("随机名称生成{}", folderName);
		File folder = new File(OJConfig.instance.tempFile, folderName);
		File mainFile = new File(folder, "Main" + language.ext);
		try {
			logger.info("将用户提交代码code=\n{}",code);
			FileUtils.write(mainFile, code);
		} catch (IOException e) {
			logger.error("将用户提交代码添加到预编译文件，异常"+e);
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
		logger.info("代码编译结果:{}", JSON.toJSON(core));
		// 题目AC/ALL
		Long pid = status.getPid();
		Problem problem= problemMapper.query(pid);
		User user = userMapper.query(status.getUid());
		// 判断题目是否通过
		if(0==core.getResult()){ // 通过
			problem.setAccepted(problem.getAccepted()+1);//问题通过+1
			problem.setSubmit(problem.getSubmit()+1);//问题总提交+1
			user.setAccepted(user.getAccepted()+1);//用户通过+1
			user.setSubmit(user.getSubmit()+1);//用户总提交+1
//			user.setSolved(Integer.parseInt(userMapper.getUserSolved(user.getUid())+""));//用户解决题数
			// TODO 需要判断用户重新提交已通过的题目
			if(0==statusMapper.isUserSolvedByPid(problem.getPid(),user.getUid())){
				user.setSolved(user.getSolved()+1);
			}
			problem.setRatio((problem.getAccepted()+0.0)/problem.getSubmit());//问题通过率
		}else{ // 未通过
			user.setSubmit(user.getSubmit()+1);//用户总提交+1
//			user.setSolved(Integer.parseInt(userMapper.getUserSolved(user.getUid())+""));//用户解决题数
			problem.setSubmit(problem.getSubmit()+1);//问题总提交
			problem.setRatio((problem.getAccepted()+0.0)/problem.getSubmit());//问题通过率
		}
		status.setMemory(core.getMemory());
		status.setResult(core.getResult());
		status.setTime(core.getTimeUsed());
		logger.info("题目提交结果信息:{}", JSON.toJSON(status));
		logger.info("编译错误信息error:{}" , core.getErrorInfo());
		statusMapper.update(status);   // 更新代码状态
		problemMapper.update(problem); // 更新题目提交率
		userMapper.update(user);//更新用户解决题数，总提交数
		deleteFloder(folder);
		logger.info("=====代码编译开始end=====");
	}

	private static final ScheduledExecutorService schedule = Executors
			.newScheduledThreadPool(0);

	/**
	 * 删除生成的编译文件
	 * @param folder
	 */
	private void deleteFloder(final File folder) {
		try {
			schedule.schedule(new Runnable() {
				public void run() {
					try {
						FileUtils.deleteDirectory(folder);
					} catch (IOException e) {
						logger.error("删除缓存文件，异常",e);
					}
				}
			}, 6, TimeUnit.SECONDS);
			FileUtils.deleteDirectory(folder);
		} catch (IOException e) {
			logger.error("删除编译后的文件，异常",e);
		}
	}
}
