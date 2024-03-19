package com.ojw.backend.controller.user.account;

import com.ojw.backend.service.in.user.account.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateUserController {
    @Autowired
    private UpdateUserService updateUserService;
    @PostMapping("/api/user/update/")
    public Map<String, String> update(@RequestParam Map<String, String> data) {
        return updateUserService.updateManager(data);
    }
}
