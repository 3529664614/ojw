package com.ojw.backend.service.impl.user.account.student;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ojw.backend.mapper.StudentMapper;
import com.ojw.backend.pojo.Student;
import com.ojw.backend.service.in.user.account.student.UpdateStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateStudentInfoServiceImpl implements UpdateStudentInfoService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Map<String, String> updateStudentInfo(Map<String, String> data) {
        Integer id = Integer.valueOf(data.get("id"));
        String address = data.get("address");
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        Student student = studentMapper.selectOne(queryWrapper);
        Map<String, String> resp = new HashMap<>();
        Student student1 = new Student(
                student.getId(),
                student.getUserId(),
                student.getUsername(),
                student.getName(),
                student.getSex(),
                student.getAge(),
                student.getCreatetime(),
                student.getEndtime(),
                address,
                student.getViolateDiscipline(),
                student.getAwarded(),
                student.getCollege(),
                student.getSpecial(),
                student.getClassroom()
        );
        studentMapper.updateById(student1);
        resp.put("error_message", "success");
        return resp;
    }
}


