package com.ojw.backend.controller.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.service.in.user.account.manager.GetCurriculumListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GetCurriculumListController {
    @Autowired
    private GetCurriculumListService getCurriculumListService;

    @GetMapping("/api/user/curriculum/getlist/")
    public JSONObject getCurriculumList(@RequestParam Map<String, String> data) {
        String page = data.get("page");
        String curriculum_lesson = data.get("curriculum_lesson");
        String curriculum_teacher_name = data.get("curriculum_teacher_name");
        if (curriculum_lesson == null) {
            curriculum_lesson = "%";
        } else {
            curriculum_lesson = '%' + curriculum_lesson.trim() + '%';
        }
        if (curriculum_teacher_name == null) {
            curriculum_teacher_name = "%";
        } else {
            curriculum_teacher_name = '%' + curriculum_teacher_name.trim() + '%';
        }
        Map<String, String> new_data = new HashMap<>();
        new_data.put("page", page);
        new_data.put("curriculum_lesson", curriculum_lesson);
        new_data.put("curriculum_teacher_name", curriculum_teacher_name);
        return getCurriculumListService.getCurriculumList(new_data);
    }
}
