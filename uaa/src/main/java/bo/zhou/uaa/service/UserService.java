package bo.zhou.uaa.service;
import bo.zhou.common.entity.RcMenu;
import bo.zhou.common.entity.RcRole;
import bo.zhou.common.entity.RcUser;
import bo.zhou.common.vo.Result;
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
    Result<RcUser> findByUsername(String username);
    /**
     * 根据角色ID查找菜单
     * @param roleId 用户角色ID
     * @return
     */
    Result<List<RcMenu>> getRolePermission(Integer roleId);
    /**
     * 根据用户ID查找角色
     * @param userId 用户ID
     * @return
     */
    Result<List<RcRole>> getRoleByUserId(Integer userId);

    /**
     * 根据用户id查询菜单
     * @param userId
     * @return
     */
    Result<List<RcMenu>> getMenuByUserId(Integer userId);

    /**
     * 根据用户id查询菜单
     * @param userName
     * @return
     */
    Result<List<RcMenu>> getMenuByUserName(String userName);
}
