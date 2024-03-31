package com.ojw.backend.controller.user.account.manager;

import com.ojw.backend.service.in.user.account.manager.AddNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddNoticeController {
    @Autowired
    private AddNoticeService addNoticeService;
    @PostMapping("/api/user/notice/add/")
    public Map<String, String> addNotice(@RequestParam Map<String, String> data) {
        return addNoticeService.addNotice(data);
    }
}
