package com.example.restfulwebservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password","ssn"})
@NoArgsConstructor
@Entity
@Table(name = "users")
//@JsonFilter("UserInfo")
public class User {
    @Schema(title ="사용자 ID", description = "사용자 ID는 자동 생성됩니다")
    @Id
    @GeneratedValue
    private Integer id;

//    @Size()/
    @Size(min=2, message="Name은 2글자로 입력해주세요")
    @Schema(description = "사용자 이름", nullable = false, example = "홍길동")
    private String name;
    @Past()
    private Date joindate;

    @JsonIgnore
    private String password;
    @JsonIgnore

    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(Integer id, String name, Date joindate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joindate = joindate;
        this.password = password;
        this.ssn = ssn;
    }
}
