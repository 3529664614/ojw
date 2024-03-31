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
public class StudentLesson {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String lesson;
    private Integer teacherId;
    private Integer studentId;
    private String teacherName;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date createtime;
    private String season;
    private String grade;
}
