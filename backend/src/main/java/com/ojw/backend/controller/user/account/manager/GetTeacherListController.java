package com.ojw.backend.controller.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.service.in.user.account.manager.GetTeacherListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GetTeacherListController {
    @Autowired
    private GetTeacherListService getTeacherListService;
    @GetMapping("/api/user/teacher/getlist/")
    public JSONObject getTeacherList(@RequestParam Map<String, String> data) {
        String page = data.get("page");
        String teacher_name = data.get("teacher_name");
        String teacher_college = data.get("teacher_college");
        if (teacher_name == null) {
            teacher_name = "%";
        } else {
            teacher_name = '%' + teacher_name.trim() + '%';
        }
        if (teacher_college == null) {
            teacher_college = "%";
        } else {
            teacher_college = '%' + teacher_college.trim() + '%';
        }
        Map<String, String> new_data = new HashMap<>();
        new_data.put("page", page);
        new_data.put("teacher_name", teacher_name);
        new_data.put("teacher_college", teacher_college);
        return getTeacherListService.getTeacherList(new_data);
    }
}
