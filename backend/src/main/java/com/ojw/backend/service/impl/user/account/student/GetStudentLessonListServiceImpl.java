package com.ojw.backend.service.impl.user.account.student;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojw.backend.mapper.StudentLessonMapper;
import com.ojw.backend.pojo.Curriculum;
import com.ojw.backend.pojo.StudentLesson;
import com.ojw.backend.service.in.user.account.student.GetStudentLessonListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetStudentLessonListServiceImpl implements GetStudentLessonListService {
    @Autowired
    private StudentLessonMapper studentLessonMapper;
    @Override
    public JSONObject getStudentLessonList(Map<String, String> data) {
        Integer page = Integer.valueOf(data.get("page"));
        String message = data.get("message");
        IPage<StudentLesson> studentLessonIPage = new Page<>(page, 10);
        String lesson_name = data.get("lesson_name");
        JSONObject resp = new JSONObject();
        QueryWrapper<StudentLesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("lesson", lesson_name);
        List<StudentLesson> student_lessons;
        if ("all".equals(message)) {
            student_lessons = studentLessonMapper.selectPage(studentLessonIPage, queryWrapper).getRecords();
        } else {
            student_lessons = studentLessonMapper.selectList(queryWrapper);
        }
        resp.put("error_message", "success");
        resp.put("student_lessons", student_lessons);
        resp.put("total_student_lessons", studentLessonMapper.selectCount(queryWrapper));
        return resp;
    }
}
