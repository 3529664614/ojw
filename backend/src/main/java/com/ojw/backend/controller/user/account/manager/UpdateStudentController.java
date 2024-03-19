package com.ojw.backend.controller.user.account.manager;

import com.ojw.backend.service.in.user.account.manager.UpdateStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
public class UpdateStudentController {
    @Autowired
    private UpdateStudentService updateStudentService;
    @PostMapping("/api/user/student/update/")
    public Map<String, String> updateStudent(@RequestParam Map<String, String> data) throws ParseException {
        return updateStudentService.updateStudent(data);
    }
}
