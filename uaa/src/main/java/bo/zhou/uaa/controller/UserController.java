package bo.zhou.uaa.controller;

import bo.zhou.common.entity.RcMenu;
import bo.zhou.common.entity.RcUser;
import bo.zhou.common.vo.Result;
import bo.zhou.uaa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

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

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping("/getUserInfo")
    public Result<RcUser> getUserInfo() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        return userService.findByUsername(userDetails.getUsername());
    }

    @GetMapping("/getUserMenus")
    public Result<List<RcMenu>> getUserMenus() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        return userService.getMenuByUserName(userDetails.getUsername());
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
