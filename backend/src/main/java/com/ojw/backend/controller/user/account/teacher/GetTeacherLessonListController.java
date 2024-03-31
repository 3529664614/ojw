package com.ojw.backend.controller.user.account.teacher;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.service.in.user.account.teacher.GetTeacherLessonListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GetTeacherLessonListController {
    @Autowired
    private GetTeacherLessonListService getTeacherLessonListService;
    @GetMapping("/api/teacher_lesson/getlist/")
    public JSONObject getTeacherList(@RequestParam Map<String, String> data) {
        String page = data.get("page");
        String teacher_name = data.get("teacher_name");
        String lesson_name = data.get("lesson_name");
        if (teacher_name == null) {
            teacher_name = "%";
        } else {
            teacher_name = '%' + teacher_name.trim() + '%';
        }
        if (lesson_name == null) {
            lesson_name = "%";
        } else {
            lesson_name = '%' + lesson_name.trim() + '%';
        }
        Map<String, String> new_data = new HashMap<>();
        new_data.put("page", page);
        new_data.put("teacher_name", teacher_name);
        new_data.put("lesson_name", lesson_name);
        return getTeacherLessonListService.getTeacherList(new_data);
    }
}
