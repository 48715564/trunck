package bo.zhou.uaa.service.impl;

import bo.zhou.common.entity.RcMenu;
import bo.zhou.common.entity.RcRole;
import bo.zhou.common.entity.RcUser;
import bo.zhou.common.vo.*;
import bo.zhou.uaa.mapper.RcMenuMapper;
import bo.zhou.uaa.mapper.RcRoleMapper;
import bo.zhou.uaa.mapper.RcUserMapper;
import bo.zhou.uaa.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: zhoubo
 * Date: 2019-2-5
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
    public Result<RcUser> findByUsername(String username) {
        QueryWrapper<RcUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        RcUser rcUser = rcUserMapper.selectOne(queryWrapper);
        Result<RcUser> result = new Result();
        if(rcUser==null){
            result.setCode(ErrorCode.BAD_REQUEST);
        }else {
            result.setCode(ErrorCode.OK);
            result.setData(rcUser);
        }
        return result;
    }
    @Override
    public Result<List<RcRole>> getRoleByUserId(Integer userId) {
        List<RcRole> rcRoles = rcRoleMapper.getRoleByUserId(userId);
        Result<List<RcRole>> result = new Result();
        result.setCode(ErrorCode.OK);
        result.setData(rcRoles);
        return result;
    }

    @Override
    public Result<List<RcMenu>> getMenuByUserId(Integer userId) {
        List<RcRole> rcRoles = rcRoleMapper.getRoleByUserId(userId);
        List<RcMenu> rcMenuList = new ArrayList<>();
        if(rcRoles!=null&&rcRoles.size()>0){
            for(RcRole rcRole : rcRoles){
                List<RcMenu> rcMenus = rcMenuMapper.getPermissionsByRoleId(rcRole.getId());
                if(rcMenus!=null&&rcMenus.size()>0){
                    rcMenuList.addAll(rcMenus);
                }
            }
        }
        Result<List<RcMenu>> result = new Result();
        result.setCode(ErrorCode.OK);
        result.setData(rcMenuList);
        return result;
    }

    @Override
    public Result<List<RcMenu>> getMenuByUserName(String userName) {
        QueryWrapper<RcUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",userName);
        RcUser rcUser = rcUserMapper.selectOne(queryWrapper);
        return getMenuByUserId(rcUser.getId());
    }

    @Override
    public Result<List<RcMenu>> getRolePermission(Integer roleId) {
        List<RcMenu> rcMenus = rcMenuMapper.getPermissionsByRoleId(roleId);
        Result<List<RcMenu>> result = new Result();
        result.setCode(ErrorCode.OK);
        result.setData(rcMenus);
        return result;
    }
}
