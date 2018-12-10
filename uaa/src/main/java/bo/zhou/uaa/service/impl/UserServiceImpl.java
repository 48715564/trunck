package bo.zhou.uaa.service.impl;

import bo.zhou.common.vo.*;
import bo.zhou.uaa.entity.*;
import bo.zhou.uaa.mapper.RcMenuMapper;
import bo.zhou.uaa.mapper.RcRoleMapper;
import bo.zhou.uaa.mapper.RcUserMapper;
import bo.zhou.uaa.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-06-13
 * Time: 10:56
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private RcUserMapper rcUserMapper;
    @Autowired
    private RcRoleMapper rcRoleMapper;
    @Autowired
    private RcMenuMapper rcMenuMapper;
    @Override
    public Result<UserVo> findByUsername(String username) {
        QueryWrapper<RcUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        RcUser rcUser = rcUserMapper.selectOne(queryWrapper);
        UserVo userVo = new UserVo();
        Result<UserVo> result = new Result();
        if(rcUser==null){
            result.setCode(ErrorCode.BAD_REQUEST);
        }else {
            BeanUtils.copyProperties(rcUser, userVo);
            result.setCode(ErrorCode.OK);
            result.setData(userVo);
        }
        return result;
    }
    @Override
    public Result<List<RoleVo>> getRoleByUserId(Integer userId) {
        List<RcRole> rcRoles = rcRoleMapper.getRoleByUserId(userId);
        List<RoleVo> roleVos = Lists.newArrayList();
        rcRoles.forEach(item->{
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(item,roleVo);
            roleVos.add(roleVo);
        });
        Result<List<RoleVo>> result = new Result();
        result.setCode(ErrorCode.OK);
        result.setData(roleVos);
        return result;
    }
    @Override
    public Result<List<MenuVo>> getRolePermission(Integer roleId) {
        List<RcMenu> rcMenus = rcMenuMapper.getPermissionsByRoleId(roleId);
        List<MenuVo> menuVos = Lists.newArrayList();
        rcMenus.forEach(item->{
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(item,menuVo);
            menuVos.add(menuVo);
        });
        Result<List<MenuVo>> result = new Result();
        result.setCode(ErrorCode.OK);
        result.setData(menuVos);
        return result;
    }
}
