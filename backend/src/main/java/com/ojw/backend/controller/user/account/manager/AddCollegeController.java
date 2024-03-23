package com.ojw.backend.controller.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.service.in.user.account.manager.AddCollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddCollegeController {
    @Autowired
    private AddCollegeService addCollegeService;

    @PostMapping("/api/user/college/add/")
    public Map<String, String> addCollege(@RequestParam Map<String, String> data) {
        return addCollegeService.addCollege(data);
    }
}
