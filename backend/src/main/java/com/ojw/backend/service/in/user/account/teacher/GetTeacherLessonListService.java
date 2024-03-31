package com.ojw.backend.service.in.user.account.teacher;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface GetTeacherLessonListService {
    JSONObject getTeacherList(Map<String, String> data);
}
