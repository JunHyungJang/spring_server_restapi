package com.example.restfulwebservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratedColumn;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post {
    @Id
//    @GeneratedColumn(strategy = GenerationType.SEQUENCE)

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;



}
