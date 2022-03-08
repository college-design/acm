package com.lxg.acm.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lxg.acm.entity.Classifier;
import com.lxg.acm.entity.ClassifierProblem;
import com.lxg.acm.mapper.ClassifierMapper;
import com.lxg.acm.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminClassifierController {

    @Autowired
    private ClassifierMapper classifierMapper;

    @RequestMapping("/classifier.action")
    public void queryClassifier1(@RequestParam(value = "page", required = false) Long page, HttpServletResponse response,
                                 @RequestParam(value = "rows", required = false) Long pageSize) throws Exception {
        Long offset = (page - 1) * pageSize;
        List<Classifier> classifierlist = classifierMapper.queryForList(null, offset,
                pageSize);
        JSONObject result = new JSONObject();
        result.put("rows", classifierlist);
        result.put("total", classifierMapper.count());
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/classifierProblem.action")
    public void queryClassifier1Problem(@RequestParam(value = "page", required = false) Long page, HttpServletResponse response,
                                        @RequestParam(value = "rows", required = false) Long pageSize) throws Exception {
        Long offset = (page - 1) * pageSize;
        List<ClassifierProblem> classifierProblemslist = classifierMapper.ClassifierProblemList(offset, pageSize);
        JSONObject result = new JSONObject();
        result.put("rows", classifierProblemslist);
        result.put("total", classifierMapper.ClassifierProblemCount());
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/addclassifier.action")
    public void addClassifier(Classifier classifier, HttpServletResponse response, Integer cid) throws Exception {
        Long r;
        if (cid != null) {
            r = classifierMapper.update(classifier);
        } else {
            r = classifierMapper.save(classifier);
        }
        JSONObject result = new JSONObject();
        if (r != 0)
            result.put("success", true);
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/addclassifierProblem.action")
    public void addClassifierProblem(ClassifierProblem classifierProblem, HttpServletResponse response, Integer cpid) throws Exception {
        Long r;
        if (cpid != null) {
            r = classifierMapper.updateClassifierProblem(classifierProblem);
        } else {
            r = classifierMapper.saveClassifierProblem(classifierProblem);
        }
        JSONObject result = new JSONObject();
        if (r != 0)
            result.put("success", true);
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/deleteClassifierProblem.action")
    public void deleteClassifierProblem(String ids, HttpServletResponse response) throws Exception {
        String[] idstr = ids.split(",");
        for (int i = 0; i < idstr.length; i++) {
            classifierMapper.deleteClassifierProblem(Long.parseLong(idstr[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

    @RequestMapping("/deleteclassifier.action")
    public void deleteClassifier(String ids, HttpServletResponse response) throws Exception {
        String[] idstr = ids.split(",");
        for (int i = 0; i < idstr.length; i++) {
            classifierMapper.delete(Long.parseLong(idstr[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
    }

}
