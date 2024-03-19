package com.ojw.backend.controller.user.account;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.service.in.user.account.GetUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetUserListController {
    @Autowired
    private GetUserListService getUserListService;
    @GetMapping("/api/user/getlist/")
    public JSONObject getList(@RequestParam Map<String, String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        String user_name = data.get("user_name");
        String user_phone = data.get("user_phone");
        if (user_name == null) {
            user_name = "%";
        } else {
            user_name = '%' + user_name.trim() + '%';
        }
        if (user_phone == null) {
            user_phone = "%";
        } else {
            user_phone = '%' + user_phone.trim() + '%';
        }
        return getUserListService.getList(page, user_name, user_phone);
    }
}
