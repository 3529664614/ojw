package com.ojw.backend.service.in.user.account.manager;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface GetSpecialListService{
    JSONObject getSpecialList(Map<String, String> data);
}
