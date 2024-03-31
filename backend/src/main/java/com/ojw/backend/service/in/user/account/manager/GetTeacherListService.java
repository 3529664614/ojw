package com.ojw.backend.service.in.user.account.manager;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface GetTeacherListService {
    JSONObject getTeacherList(Map<String, String> data);
}
