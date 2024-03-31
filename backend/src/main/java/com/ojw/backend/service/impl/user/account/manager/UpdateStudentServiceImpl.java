package com.ojw.backend.service.impl.user.account.manager;

import com.ojw.backend.mapper.StudentMapper;
import com.ojw.backend.mapper.UserMapper;
import com.ojw.backend.pojo.Student;
import com.ojw.backend.pojo.User;
import com.ojw.backend.service.in.user.account.manager.UpdateStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateStudentServiceImpl implements UpdateStudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> updateStudent(Map<String, String> data) throws ParseException {
        Integer id = Integer.valueOf(data.get("student_id"));
        Integer user_id = Integer.valueOf(data.get("student_user_id"));
        String username = data.get("student_username");
        String name = data.get("student_name");
        String age = data.get("student_age");
        String sex = data.get("student_sex");
        String college = data.get("student_college");
        String special = data.get("student_special");
        String classroom = data.get("student_classroom");
        String createtime1 = data.get("student_createtime");
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date createtime = fmt.parse(createtime1);
        String endtime1 = data.get("student_endtime");
        Date endtime = fmt.parse(endtime1);
        String address = data.get("student_address");
        String violate_discipline = data.get("student_violate_discipline");
        String awarded = data.get("student_awarded");
        Student student = new Student(
                id,
                user_id,
                username,
                name,
                sex,
                age,
                createtime,
                endtime,
                address,
                violate_discipline,
                awarded,
                college,
                special,
                classroom
        );

        studentMapper.updateById(student);
        User user = userMapper.selectById(user_id);
        user.setSex(sex);
        user.setName(name);
        user.setAge(age);
        userMapper.updateById(user);
        Map<String, String> new_data = new HashMap<>();
        new_data.put("error_message", "success");
        return new_data;

    }
}
