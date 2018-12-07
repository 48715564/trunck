package bo.zhou.user.service;

import bo.zhou.user.entity.RcMenu;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-06-13
 * Time: 10:12
 */
public interface PermissionService {
    List<RcMenu> getPermissionsByRoleId(Integer roleId);
}
