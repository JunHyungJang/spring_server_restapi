package com.example.restfulwebservice.dao;

import com.example.restfulwebservice.bean.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usercount  = 3;
    static {

        users.add(new User(1,"JUN",new Date(),"test1","1234"));
        users.add(new User(2,"JUN",new Date(),"test2","5678"));
        users.add(new User(3,"JUN",new Date(),"test3","0123"));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if (user.getId()==null) {
            user.setId(++usercount);

        }
        users.add(user);
        return user;
    }
    public User findone(int id){
        for (User user:users){
            if (user.getId()==id){
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()){
            User user = iterator.next();

            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
