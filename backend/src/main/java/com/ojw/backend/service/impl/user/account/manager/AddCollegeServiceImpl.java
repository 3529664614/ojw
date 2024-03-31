package com.ojw.backend.service.impl.user.account.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ojw.backend.mapper.CollegeMapper;
import com.ojw.backend.pojo.College;
import com.ojw.backend.service.in.user.account.manager.AddCollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddCollegeServiceImpl implements AddCollegeService {
    @Autowired
    private CollegeMapper collegeMapper;
    @Override
    public Map<String, String> addCollege(Map<String, String> data) {
        String college_name = data.get("college_name");
        Map<String, String> resp = new HashMap<>();
        if (college_name == null) {
            resp.put("error_message", "学院名不能为空");
            return resp;
        }
        if (college_name.length() == 0) {
            resp.put("error_message", "学院名不能为空");
            return resp;
        }
        QueryWrapper<College> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", college_name);
        College college1 = collegeMapper.selectOne(queryWrapper);
        if (college1 != null) {
            resp.put("error_message", "该学院名已经使用过");
            return resp;
        }
        college_name.trim();
        College college = new College(null, college_name);
        collegeMapper.insert(college);
        resp.put("error_message", "success");
        return resp;
    }
}
