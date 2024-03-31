package com.ojw.backend.service.impl.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.mapper.SpecialMapper;
import com.ojw.backend.pojo.Special;
import com.ojw.backend.service.in.user.account.manager.AddCollegeSpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddCollegeSpecialServiceImpl implements AddCollegeSpecialService {
    @Autowired
    private SpecialMapper specialMapper;
    @Override
    public Map<String, String> addCollegeSpecial(Map<String, String> data) {
        String college_type = data.get("college_type");
        String special = data.get("special");
        Map<String, String> resp = new HashMap<>();
        if (college_type == null || special == null) {
            resp.put("error_message", "学院或者专业不能为空");
            return resp;
        }
        if (college_type.length() == 0 || special.length() == 0) {
            resp.put("error_message", "学院或者专业不能为空");
            return resp;
        }
        college_type.trim();
        special.trim();
        Special special1 = new Special(
                null,
                college_type,
                special
        );
        specialMapper.insert(special1);
        resp.put("error_message", "success");
        return resp;
    }

}
