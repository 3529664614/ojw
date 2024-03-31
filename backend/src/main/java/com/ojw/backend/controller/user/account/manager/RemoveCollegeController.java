package com.ojw.backend.controller.user.account.manager;

import com.ojw.backend.service.in.user.account.manager.RemoveCollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoveCollegeController {
    @Autowired
    private RemoveCollegeService removeCollegeService;
    @PostMapping("/api/user/college/remove/")
    public Map<String, String> removeCollege(@RequestParam Map<String, String> data) {
        return removeCollegeService.removeCollege(data);
    }
}
