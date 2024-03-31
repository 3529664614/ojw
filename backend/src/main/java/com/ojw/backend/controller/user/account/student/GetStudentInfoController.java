package com.ojw.backend.controller.user.account.student;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.service.in.user.account.student.GetStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetStudentInfoController {
    @Autowired
    private GetStudentInfoService getStudentInfoService;
    @GetMapping("/api/user/student/get/")
    public JSONObject getStudentInfo(@RequestParam Map<String, String> data) {
        return getStudentInfoService.getStudentInfo(data);
    }
}
