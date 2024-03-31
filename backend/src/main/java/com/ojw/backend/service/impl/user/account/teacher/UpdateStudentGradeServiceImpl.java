package com.ojw.backend.service.impl.user.account.teacher;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ojw.backend.mapper.StudentLessonMapper;
import com.ojw.backend.pojo.StudentLesson;
import com.ojw.backend.service.in.user.account.teacher.UpdateStudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateStudentGradeServiceImpl implements UpdateStudentGradeService {
    @Autowired
    private StudentLessonMapper studentLessonMapper;
    @Override
    public Map<String, String> updateStudentGrade(Map<String, String> data) {
        Integer studentId = Integer.valueOf(data.get("id"));
        String grade = data.get("grade");
        Map<String, String> resp = new HashMap<>();
        QueryWrapper<StudentLesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("student_id", studentId);
        StudentLesson studentLesson = studentLessonMapper.selectOne(queryWrapper);
        StudentLesson studentLesson1 = new StudentLesson(
                studentLesson.getId(),
                studentLesson.getLesson(),
                studentLesson.getTeacherId(),
                studentLesson.getStudentId(),
                studentLesson.getTeacherName(),
                studentLesson.getName(),
                studentLesson.getCreatetime(),
                studentLesson.getSeason(),
                grade
        );
        studentLessonMapper.updateById(studentLesson1);
        resp.put("error_message", "success");
        return resp;
    }
}
