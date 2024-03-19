package com.ojw.backend.controller.user.account;

import com.ojw.backend.service.in.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @GetMapping("/api/user/account/login/")
    public Map<String, String> login() {
        return loginService.login();
    }

}
