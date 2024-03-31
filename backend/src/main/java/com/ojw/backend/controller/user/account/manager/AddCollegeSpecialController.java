package com.ojw.backend.controller.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.service.in.user.account.manager.AddCollegeSpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddCollegeSpecialController {
    @Autowired
    private AddCollegeSpecialService addCollegeSpecialService;
    @PostMapping("/api/user/college/special/add/")
    public Map<String, String> addCollegeSpecial(@RequestParam Map<String, String> data) {
        return addCollegeSpecialService.addCollegeSpecial(data);
    }
}
