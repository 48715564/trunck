package bo.zhou.uaa.service.impl;

import bo.zhou.uaa.service.UserService;
import bo.zhou.common.vo.MenuVo;
import bo.zhou.common.vo.Result;
import bo.zhou.common.vo.RoleVo;
import bo.zhou.common.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhoubo
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<UserVo> userResult = userService.findByUsername(username);
        if (userResult.getCode() == 100) {
            throw new UsernameNotFoundException("用户:" + username + ",不存在!");
        }
       Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userResult.getData(), userVo);
        Result<List<RoleVo>> roleResult = userService.getRoleByUserId(userVo.getId());
        if (roleResult.getCode() != 100) {
            List<RoleVo> roleVoList = roleResult.getData();
            for (RoleVo role : roleVoList) {
                //角色必须是ROLE_开头，可以在数据库中设置
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getValue());
                grantedAuthorities.add(grantedAuthority);
                //获取权限
                Result<List<MenuVo>> perResult = userService.getRolePermission(role.getId());
                if (perResult.getCode() != 100) {
                    List<MenuVo> permissionList = perResult.getData();
                    for (MenuVo menu : permissionList
                    ) {
                        GrantedAuthority authority = new SimpleGrantedAuthority(menu.getCode());
                        grantedAuthorities.add(authority);
                    }
                }
            }
        }
        User user = new User(userVo.getUsername(), userVo.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }
}
