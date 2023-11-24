package com.example.restfulwebservice.helloworld.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private UserDaoService service;

    public AdminUserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {

        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joindate","ssn","password");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }

//    @GetMapping("/v1/users/{id}")
    //id가 올떄는 string으로 오는데 int로 mapping 함수내에서 자동으로됨
//    @GetMapping(value = "/users/{id}/", params = "version=1")
//    @GetMapping(value = "/users/{id}",headers = "X-API-VERSION=1")
    @GetMapping(value = "/v1/users/{id}",produces =  "application/vnd.company.appv1+json")

    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        User user = service.findone(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%S] not found",id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joindate","ssn","password");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);
        return mapping;
    }
//    @GetMapping("/v2/users/{id}")
//    @GetMapping(value = "/users/{id}/", params = "version=2")
//    @GetMapping(value = "/users/{id}",headers = "X-API-VERSION=2")
    @GetMapping(value = "/v2/users/{id}",produces =  "application/vnd.company.appv2+json")

    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        User user = service.findone(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%S] not found",id));
        }

        UserV2 userV2 = new UserV2();
//        UserV2.setGrade("VIP");
        //Beanutils 같은 두 bean 들간의 상호작용?
        BeanUtils.copyProperties(user, userV2); // id, name, joinDate, password, ssn
        userV2.setGrade("VIP");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joindate","grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);
        return mapping;
    }
}
