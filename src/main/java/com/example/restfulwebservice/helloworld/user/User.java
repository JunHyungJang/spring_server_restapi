package com.example.restfulwebservice.helloworld.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password","ssn"})
@NoArgsConstructor
//@JsonFilter("UserInfo")
public class User {
    private Integer id;

//    @Size()/
    @Size(min=2, message="Name은 2글자로 입력해주세요")
    @Schema(description = "사용자 이름", nullable = false, example = "홍길동")
    private String name;
    @Past()
    private Date joindate;

//    @JsonIgnore
    private String password;
//    @JsonIgnore

    private String ssn;


}
