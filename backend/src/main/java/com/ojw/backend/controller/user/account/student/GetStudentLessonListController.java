package com.ojw.backend.controller.user.account.student;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.service.in.user.account.student.GetStudentLessonListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GetStudentLessonListController {
    @Autowired
    private GetStudentLessonListService getStudentLessonListService;
    @GetMapping("/api/student_lesson/getlist/")
    public JSONObject getStudentLessonList(@RequestParam Map<String, String> data) {
        String page = data.get("page");
        String lesson_name = data.get("lesson_name");

        if (lesson_name == null) {
            lesson_name = "%";
        } else {
            lesson_name = '%' + lesson_name.trim() + '%';
        }
        Map<String, String> new_data = new HashMap<>();
        new_data.put("page", page);
        new_data.put("lesson_name", lesson_name);
        return getStudentLessonListService.getStudentLessonList(new_data);
    }
}
