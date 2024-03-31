package com.ojw.backend.service.in.user.account.manager;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface GetNoticeListService {
    JSONObject getNoticeList(Map<String, String> data);
}
