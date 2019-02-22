package bo.zhou.uaa.controller;

import bo.zhou.common.vo.Result;
import bo.zhou.uaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author zhoubo
 */
@RestController
@RequestMapping("/api")
public class UserController {
    private static final String BEARER = "Bearer ";
    @Autowired
    TokenStore tokenStore;
    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping("/getUserByToken")
    public Result getUserByToken(@RequestHeader("Authorization") String authorization) {
        if(authorization.startsWith(BEARER)){
            authorization = authorization.substring(BEARER.length());
        }
        Object user = tokenStore.readAuthentication(authorization).getPrincipal();
        if(user instanceof User){
            return userService.findByUsername(((User) user).getUsername());
        }
        return Result.failure(400,"用户不存在！");
    }
}
