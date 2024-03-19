package com.ojw.backend.service.in.user.account.manager;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface GetCollegeListService {
    JSONObject getCollegeList(Map<String, String> data);
}
