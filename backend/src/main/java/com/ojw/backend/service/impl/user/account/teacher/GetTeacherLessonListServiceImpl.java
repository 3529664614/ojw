package com.ojw.backend.service.impl.user.account.teacher;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojw.backend.mapper.CurriculumMapper;
import com.ojw.backend.pojo.Curriculum;
import com.ojw.backend.service.in.user.account.teacher.GetTeacherLessonListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetTeacherLessonListServiceImpl implements GetTeacherLessonListService {
    @Autowired
    private CurriculumMapper curriculumMapper;
    @Override
    public JSONObject getTeacherList(Map<String, String> data) {
        Integer page = Integer.valueOf(data.get("page"));
        IPage<Curriculum> curriculumIPage = new Page<>(page, 10);
        String teacher_name = data.get("teacher_name");
        String lesson_name = data.get("lesson_name");
        JSONObject resp = new JSONObject();
        QueryWrapper<Curriculum> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("teacher", teacher_name);
        queryWrapper.like("lesson", lesson_name);
        List<Curriculum> teacher_lessons = curriculumMapper.selectPage(curriculumIPage, queryWrapper).getRecords();
        resp.put("error_message", "success");
        resp.put("teacher_lessons", teacher_lessons);
        resp.put("total_teacher_lessons", curriculumMapper.selectCount(queryWrapper));
        return resp;
    }
}
