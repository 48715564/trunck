package bo.zhou.user.service.impl;

import bo.zhou.user.entity.RcMenu;
import bo.zhou.user.mapper.RcMenuMapper;
import bo.zhou.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-06-13
 * Time: 10:12
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private RcMenuMapper menuMapper;
    @Override
    public List<RcMenu> getPermissionsByRoleId(Integer roleId) {
        return menuMapper.getPermissionsByRoleId(roleId);
    }
}
