package com.example.restfulwebservice.repository;

import com.example.restfulwebservice.bean.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
}
