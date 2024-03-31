package com.ojw.backend.service.in.user.account.manager;

import java.text.ParseException;
import java.util.Map;

public interface UpdateCurriculumService {
    Map<String, String> updateCurriculum(Map<String, String> data) throws ParseException;
}
