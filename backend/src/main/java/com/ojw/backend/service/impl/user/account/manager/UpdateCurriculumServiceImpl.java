package com.ojw.backend.service.impl.user.account.manager;

import com.ojw.backend.mapper.CurriculumMapper;
import com.ojw.backend.mapper.TeacherMapper;
import com.ojw.backend.pojo.Curriculum;
import com.ojw.backend.service.in.user.account.manager.UpdateCurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service

public class UpdateCurriculumServiceImpl implements UpdateCurriculumService {
    @Autowired
    private CurriculumMapper curriculumMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Map<String, String> updateCurriculum(Map<String, String> data) throws ParseException {
        Integer id = Integer.valueOf(data.get("curriculum_id"));
        Integer teacherId = Integer.valueOf(data.get("curriculum_teacher_id"));
        String lesson = data.get("curriculum_lesson");
        String teacher = data.get("curriculum_teacher");
        String classroom = data.get("curriculum_classroom");
        String season = data.get("curriculum_season");
        String content = data.get("curriculum_content");
        String createtime1 = data.get("curriculum_createtime");
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date createtime = fmt.parse(createtime1);
        Curriculum curriculum = new Curriculum(
                id,
                teacherId,
                teacher,
                createtime,
                lesson,
                season,
                classroom,
                content
        );
        curriculumMapper.updateById(curriculum);
        Map<String, String> resp = new HashMap<>();
        resp.put("error_message", "success");
        return resp;
    }
}
