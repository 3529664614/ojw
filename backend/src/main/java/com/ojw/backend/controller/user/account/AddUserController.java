package com.ojw.backend.controller.user.account;

import com.ojw.backend.service.in.user.account.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddUserController {
    @Autowired
    private AddUserService addUserService;
    @PostMapping("/api/user/add/")
    public Map<String, String> adduser(@RequestParam Map<String, String> data) {
        return addUserService.adduser(data);
    }
}
