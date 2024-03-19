package com.ojw.backend.service.in.user.account;

import com.alibaba.fastjson.JSONObject;

public interface GetUserListService {
    JSONObject getList(Integer page, String user_name, String user_phone);
}
