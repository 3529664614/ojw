package com.ojw.backend.controller.user.account.manager;

import com.alibaba.fastjson.JSONObject;
import com.ojw.backend.service.in.user.account.manager.GetCollegeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GetCollegeListController {
    @Autowired
    private GetCollegeListService getCollegeListService;
    @GetMapping("/api/user/college/getlist/")
    public JSONObject getCollegeList(@RequestParam Map<String, String> data) {
        String page = data.get("page");
        String college_name = data.get("college_name");
        if (college_name == null) {
            college_name = "%";
        } else {
            college_name = '%' + college_name.trim() + '%';
        }
        Map<String, String> new_data = new HashMap<>();
        new_data.put("page", page);
        new_data.put("college_name", college_name);
        return getCollegeListService.getCollegeList(new_data);
    }
}
