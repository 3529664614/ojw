package com.ojw.backend.controller.user.account.manager;

import com.ojw.backend.service.in.user.account.manager.UpdateCurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
public class UpdateCurriculumController {
    @Autowired
    private UpdateCurriculumService updateCurriculumService;
    @PostMapping("/api/user/curriculum/update/")
    public Map<String, String> updateCurriculum(@RequestParam Map<String, String> data) throws ParseException {
        return updateCurriculumService.updateCurriculum(data);
    }
}
