package com.ojw.backend.service.impl.user.account.manager;

import com.ojw.backend.mapper.TeacherMapper;
import com.ojw.backend.pojo.Teacher;
import com.ojw.backend.service.in.user.account.manager.UpdateTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateTeacherServiceImpl implements UpdateTeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public Map<String, String> updateTeacher(Map<String, String> data) throws ParseException {
        Integer id = Integer.valueOf(data.get("teacher_id"));
        String username = data.get("teacher_username");
        String name = data.get("teacher_name");
        String age = data.get("teacher_age");
        String sex = data.get("teacher_sex");
        String college = data.get("teacher_college");
        String createtime1 = data.get("teacher_createtime");
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date createtime = fmt.parse(createtime1);
        String work_age = data.get("teacher_work_age");
        Teacher teacher = new Teacher(
                id,
                name,
                username,
                sex,
                age,
                college,
                createtime,
                work_age
        );
        teacherMapper.updateById(teacher);
        Map<String, String> resp = new HashMap<>();
        resp.put("error_message", "success");
        return resp;
    }
}
