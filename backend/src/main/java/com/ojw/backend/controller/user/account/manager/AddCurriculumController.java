package com.ojw.backend.controller.user.account.manager;

import com.ojw.backend.service.in.user.account.manager.AddCurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
public class AddCurriculumController {
    @Autowired
    private AddCurriculumService addCurriculumService;
    @PostMapping("/api/user/curriculum/add/")
    public Map<String, String> addCurriculum(@RequestParam Map<String, String> data) throws ParseException {
        return addCurriculumService.addCurriculum(data);
    }
}
