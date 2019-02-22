package bo.zhou.service.controller;

import bo.zhou.service.client.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private UserApi userApi;
    @GetMapping("/user")
    public Principal user(Principal user){
        System.out.println(userApi.test());
        System.out.println(user);
        return user;
    }
}
