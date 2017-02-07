package com.lxg.acm.controller.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lxg.acm.mapper.ClassifierMapper;
import com.lxg.acm.mapper.ContestMapper;
import com.lxg.acm.mapper.ProblemMapper;
import com.lxg.acm.mapper.StatusMapper;
import com.lxg.acm.mapper.UserMapper;

@Component
public class BaseJsonController {

	@Autowired
	protected ProblemMapper problemMapper;

	@Autowired
	protected StatusMapper statusMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
	protected ClassifierMapper classifierMapper;

	@Autowired
	protected ContestMapper contestMapper;

}
