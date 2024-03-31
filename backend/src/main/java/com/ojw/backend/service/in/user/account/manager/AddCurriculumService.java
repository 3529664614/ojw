package com.ojw.backend.service.in.user.account.manager;

import java.text.ParseException;
import java.util.Map;

public interface AddCurriculumService {
    Map<String, String> addCurriculum(Map<String, String> data) throws ParseException;
}
