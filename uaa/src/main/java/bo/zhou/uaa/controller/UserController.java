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
 * <p>必须要有，做验证</p>
 * Created by Mr.Yangxiufeng on 2017/12/29.
 * Time:10:43
 * ProjectName:Mirco-Service-Skeleton
 */
@RestController
public class UserController {
    @Autowired
    TokenStore tokenStore;
    @Autowired
    UserService userService;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        System.out.println(user);
        return user;
    }

    @RequestMapping("/getUserByToken")
    public Result getUserByToken(@RequestHeader("Authorization") String authorization) {
        if(authorization.startsWith("Bearer ")){
            authorization = authorization.substring("Bearer ".length());
        }
        Object user = tokenStore.readAuthentication(authorization).getPrincipal();
        if(user instanceof User){
            return userService.findByUsername(((User) user).getUsername());
        }
        return Result.failure(400,"用户不存在！");
    }
}
