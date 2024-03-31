package com.ojw.backend.service.in.user.account.student;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface GetStudentLessonListService {
    JSONObject getStudentLessonList(Map<String, String> data);
}
