package bo.zhou.uaa.service.impl;

import bo.zhou.common.entity.RcMenu;
import bo.zhou.common.entity.RcRole;
import bo.zhou.common.entity.RcUser;
import bo.zhou.common.vo.ErrorCode;
import bo.zhou.common.vo.Result;
import bo.zhou.uaa.service.UserService;
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
        Result<RcUser> userResult = userService.findByUsername(username);
        if (userResult.getCode() == ErrorCode.BAD_REQUEST) {
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
        Result<List<RcRole>> roleResult = userService.getRoleByUserId(userResult.getData().getId());
        List<RcRole> roleVoList = roleResult.getData();
        for (RcRole role : roleVoList) {
            //角色必须是ROLE_开头，可以在数据库中设置
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getValue());
            grantedAuthorities.add(grantedAuthority);
            //获取权限
            Result<List<RcMenu>> perResult = userService.getRolePermission(role.getId());
            if (perResult.getCode() != 100) {
                List<RcMenu> permissionList = perResult.getData();
                for (RcMenu menu : permissionList) {
                    GrantedAuthority authority = new SimpleGrantedAuthority(menu.getCode());
                    grantedAuthorities.add(authority);
                }
            }
        }
        User user = new User(userResult.getData().getUsername(), userResult.getData().getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }
}
