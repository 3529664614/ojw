package com.ojw.backend.service.impl.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojw.backend.mapper.CurriculumMapper;
import com.ojw.backend.pojo.Curriculum;
import com.ojw.backend.service.in.user.account.manager.GetCurriculumListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetCurriculumListServiceImpl implements GetCurriculumListService {
    @Autowired
    private CurriculumMapper curriculumMapper;
    @Override
    public JSONObject getCurriculumList(Map<String, String> data) {
        Integer page = Integer.valueOf(data.get("page"));
        IPage<Curriculum> curriculumIPage = new Page<>(page, 10);
        String teacher_name = data.get("curriculum_teacher_name");
        String lesson = data.get("curriculum_lesson");
        JSONObject resp = new JSONObject();
        QueryWrapper<Curriculum> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.like("teacher", teacher_name);
        queryWrapper.like("lesson", lesson);
        List<Curriculum> curriculums = curriculumMapper.selectPage(curriculumIPage, queryWrapper).getRecords();
        resp.put("error_message", "success");
        resp.put("curriculums", curriculums);
        resp.put("total_curriculums", curriculumMapper.selectCount(queryWrapper));
        return resp;
    }
}
