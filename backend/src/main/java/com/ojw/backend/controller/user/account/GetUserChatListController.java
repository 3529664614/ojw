package com.ojw.backend.controller.user.account;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ojw.backend.mapper.UserMapper;
import com.ojw.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetUserChatListController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/api/user/chat/getlist/")
    public JSONObject getUserChatList(@RequestParam Map<String, String> data) {
        String user_type = data.get("user_type");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_type", user_type);
        List<User> users = userMapper.selectList(queryWrapper);
        JSONObject resp = new JSONObject();
        resp.put("error_message", "success");
        resp.put("users", users);
        return resp;
    }
}
