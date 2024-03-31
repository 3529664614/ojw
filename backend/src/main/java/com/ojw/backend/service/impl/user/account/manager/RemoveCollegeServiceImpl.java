package com.ojw.backend.service.impl.user.account.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ojw.backend.mapper.CollegeMapper;
import com.ojw.backend.mapper.SpecialMapper;
import com.ojw.backend.pojo.College;
import com.ojw.backend.pojo.Special;
import com.ojw.backend.service.in.user.account.manager.RemoveCollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveCollegeServiceImpl implements RemoveCollegeService {
    @Autowired
    private CollegeMapper collegeMapper;
    @Autowired
    private SpecialMapper specialMapper;
    @Override
    public Map<String, String> removeCollege(Map<String, String> data) {
        String college_type = data.get("college_type");
        QueryWrapper<College> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("type", college_type);
        QueryWrapper<Special> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("type", college_type);
        collegeMapper.delete(queryWrapper1);
        specialMapper.delete(queryWrapper2);
        Map<String, String> resp = new HashMap<>();
        resp.put("error_message", "success");
        return resp;
    }
}
