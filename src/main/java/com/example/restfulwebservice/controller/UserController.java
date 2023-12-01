package com.example.restfulwebservice.controller;

import com.example.restfulwebservice.bean.User;
import com.example.restfulwebservice.dao.UserDaoService;
import com.example.restfulwebservice.bean.UserNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDaoService service;
    public static class MyEntityModel<T> extends EntityModel<T> {
        public MyEntityModel(T content) {
            super(content);
        }
    }
    public UserController(UserDaoService service){
        this.service = service;

    }
    @GetMapping("/users")
    public List<User> retrieveAllusers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    //id가 올떄는 string으로 오는데 int로 mapping 함수내에서 자동으로됨
    public MyEntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findone(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%S] not found",id));
        }

        //Hateos
        //하나의 기능에서 사용할수있는 다양한 api를 알 수 있음
        MyEntityModel<User> entityModel = new MyEntityModel<>(user);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllusers());

        entityModel.add(linkTo.withRel("all-users"));
        return entityModel;
    }
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = service.save(user);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();


    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%S] not found",id));
        }
    }
}
