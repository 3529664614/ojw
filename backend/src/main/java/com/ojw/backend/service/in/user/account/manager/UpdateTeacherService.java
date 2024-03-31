package com.ojw.backend.service.in.user.account.manager;

import java.text.ParseException;
import java.util.Map;

public interface UpdateTeacherService {
    Map<String, String> updateTeacher(Map<String, String> data) throws ParseException;
}
