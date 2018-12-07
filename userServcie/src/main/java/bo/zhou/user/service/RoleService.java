package bo.zhou.user.service;


import bo.zhou.user.entity.RcRole;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-05-10
 * Time: 20:26
 */
public interface RoleService {
    List<RcRole> getRoleByUserId(Integer userId);
}
