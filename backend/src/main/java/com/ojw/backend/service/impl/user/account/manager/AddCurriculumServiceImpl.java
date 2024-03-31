package com.ojw.backend.service.impl.user.account.manager;

import com.ojw.backend.mapper.CurriculumMapper;
import com.ojw.backend.pojo.Curriculum;
import com.ojw.backend.service.in.user.account.manager.AddCurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddCurriculumServiceImpl implements AddCurriculumService {
    @Autowired
    private CurriculumMapper curriculumMapper;
    @Override
    public Map<String, String> addCurriculum(Map<String, String> data) throws ParseException {
        System.out.println(data);
        String lesson = data.get("lesson");
        Integer teacher_id = Integer.valueOf(data.get("teacher_id"));
        String teacher = data.get("teacher");
        String classroom = data.get("classroom");
        String season = data.get("season");
        String content = data.get("content");
        String createtime1 = data.get("createtime");
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date createtime = fmt.parse(createtime1);
        Map<String, String> resp = new HashMap<>();
        Curriculum curriculum = new Curriculum(
                null,
                teacher_id,
                teacher,
                createtime,
                lesson,
                season,
                classroom,
                content
        );
        curriculumMapper.insert(curriculum);
        resp.put("error_message", "success");
        return resp;
    }
}
