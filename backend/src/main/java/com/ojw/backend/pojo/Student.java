package com.ojw.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class Student {
    @TableId (type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String username;
    private String name;
    private String sex;
    private String age;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date endtime;
    private String address;
    private String violateDiscipline;
    private String awarded;
    private String college;
    private String special;
    private String classroom;
}
