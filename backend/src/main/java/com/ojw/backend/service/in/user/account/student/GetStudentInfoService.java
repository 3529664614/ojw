package com.ojw.backend.service.in.user.account.student;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface GetStudentInfoService {
    JSONObject getStudentInfo(Map<String, String> data);
}
