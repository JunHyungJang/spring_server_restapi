package com.example.restfulwebservice.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfoV2")
public class UserV2 extends User {
    private String grade;

}
//    public UserV2() {
//
//    }

