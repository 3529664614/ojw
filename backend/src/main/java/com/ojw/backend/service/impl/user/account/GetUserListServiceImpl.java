package com.ojw.backend.service.impl.user.account;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ojw.backend.mapper.UserMapper;
import com.ojw.backend.pojo.User;
import com.ojw.backend.service.in.user.account.GetUserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserListServiceImpl implements GetUserListService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public JSONObject getList(Integer page, String user_name, String user_phone) {
        IPage<User> userIpage = new Page<>(page, 10);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.like("name", user_name);
        queryWrapper.like("phone", user_phone);
        List<User> users = userMapper.selectPage(userIpage, queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        for (User user: users) {
            user.setPassword("");
        }
        resp.put("users", users);
        resp.put("users_count", userMapper.selectCount(queryWrapper));
        return resp;
    }
}
