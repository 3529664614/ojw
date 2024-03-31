package com.ojw.backend.controller.user.account.teacher;

import com.ojw.backend.service.in.user.account.teacher.UpdateStudentGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateStudentGradeController {
    @Autowired
    private UpdateStudentGradeService updateStudentGradeService;
    @PostMapping("/api/user/teacher/student/grade/update/")
    public Map<String, String> updateStudentGrade(@RequestParam Map<String, String> data) {
        return updateStudentGradeService.updateStudentGrade(data);
    }
}
