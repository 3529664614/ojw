package com.ojw.backend.controller.user.account.student;

import com.ojw.backend.service.in.user.account.student.UpdateStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateStudentInfoController {
    @Autowired
    private UpdateStudentInfoService updateStudentInfoService;
    @PostMapping("/api/user/student/info/update/")
    public Map<String, String> updateStudentInfo(@RequestParam Map<String, String> data) {
        return updateStudentInfoService.updateStudentInfo(data);
    }
}
