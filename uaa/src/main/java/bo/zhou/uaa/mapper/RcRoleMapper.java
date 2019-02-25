package bo.zhou.uaa.mapper;

import bo.zhou.common.entity.RcRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubo
 * @since 2018-12-10
 */
public interface RcRoleMapper extends BaseMapper<RcRole> {
    @Select(value = "select role.* from rc_role role,rc_user_role ur where role.id=ur.role_id and ur.user_id=#{userId}")
    List<RcRole> getRoleByUserId(Integer userId);
}
