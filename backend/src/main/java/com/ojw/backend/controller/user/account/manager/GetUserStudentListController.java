package com.ojw.backend.controller.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.service.in.user.account.manager.GetStudentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GetUserStudentListController {
    @Autowired
    private GetStudentListService getStudentListService;
    @GetMapping("/api/user/student/getlist/")
    public JSONObject getStudentList(@RequestParam Map<String, String> data) {
        String page = data.get("page");
        String student_name = data.get("student_name");
        String student_college = data.get("student_college");
        if (student_name == null) {
            student_name = "%";
        } else {
            student_name = '%' + student_name.trim() + '%';
        }
        if (student_college == null) {
            student_college = "%";
        } else {
            student_college = '%' + student_college.trim() + '%';
        }
        Map<String, String> new_data = new HashMap<>();
        new_data.put("page", page);
        new_data.put("student_name", student_name);
        new_data.put("student_college", student_college);
        return getStudentListService.getStudentList(new_data);
    }
}
