package bo.zhou.uaa.service;
import bo.zhou.common.vo.MenuVo;
import bo.zhou.common.vo.Result;
import bo.zhou.common.vo.RoleVo;
import bo.zhou.common.vo.UserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author zhoubo
 */
public interface UserService {
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return
     */
    Result<UserVo> findByUsername(String username);
    /**
     * 根据角色ID查找菜单
     * @param roleId 用户角色ID
     * @return
     */
    Result<List<MenuVo>> getRolePermission(Integer roleId);
    /**
     * 根据用户ID查找角色
     * @param userId 用户ID
     * @return
     */
    Result<List<RoleVo>> getRoleByUserId(Integer userId);
}
