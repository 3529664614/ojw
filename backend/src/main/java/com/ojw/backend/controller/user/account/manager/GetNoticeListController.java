package com.ojw.backend.controller.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.service.in.user.account.manager.GetNoticeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetNoticeListController {
    @Autowired
    private GetNoticeListService getNoticeListService;
    @GetMapping("/api/user/notice/getlist/")
    public JSONObject getNoticeList(@RequestParam Map<String, String> data) {
        return getNoticeListService.getNoticeList(data);
    }
}
