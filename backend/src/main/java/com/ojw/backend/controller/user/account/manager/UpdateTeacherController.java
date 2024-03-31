package com.ojw.backend.controller.user.account.manager;

import com.ojw.backend.service.in.user.account.manager.UpdateTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
public class UpdateTeacherController {
    @Autowired
    private UpdateTeacherService updateTeacherService;
    @PostMapping("/api/user/teacher/update/")
    public Map<String, String> updateTeacher(@RequestParam Map<String, String> data) throws ParseException {
        return updateTeacherService.updateTeacher(data);
    }
}
