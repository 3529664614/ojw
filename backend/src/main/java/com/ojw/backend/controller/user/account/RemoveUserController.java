package com.ojw.backend.controller.user.account;

import com.ojw.backend.service.in.user.account.RemoveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoveUserController {
    @Autowired
    private RemoveUserService removeUserService;
    @PostMapping("/api/user/remove/")
    public Map<String, String> remove(@RequestParam Map<String, String> data) {
        return removeUserService.remove(data);
    }
}
