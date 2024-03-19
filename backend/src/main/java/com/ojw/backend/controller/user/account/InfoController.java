package com.ojw.backend.controller.user.account;

import com.ojw.backend.service.in.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;
    @PostMapping("/api/user/account/token/")
    public Map<String, String> getToken(@RequestParam Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");
        return infoService.getinfo(username, password);
    }
}
