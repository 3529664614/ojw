package com.ojw.backend.controller.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.service.in.user.account.manager.GetSpecialListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetSpecialListController {
    @Autowired
    private GetSpecialListService getSpecialListService;
    @GetMapping("/api/user/special/getlist/")
    public JSONObject getSpecialList(@RequestParam Map<String, String> data) {
        return getSpecialListService.getSpecialList(data);
    }
}
