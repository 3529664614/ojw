package com.ojw.backend.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OffMessage {
    private Integer id;
    private Integer toId;
    private String chatContent;

//    @JSONField(format="yyyy-MM-dd HH:mm:ss")
//    public Date time;
}
