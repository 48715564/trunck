package bo.zhou.uaa.mapper;

import bo.zhou.uaa.entity.RcMenu;
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
public interface RcMenuMapper extends BaseMapper<RcMenu> {
    @Select(value = "select menu.* from rc_menu menu,rc_privilege p where menu.id=p.menu_id and p.role_id=#{roleId}")
    List<RcMenu> getPermissionsByRoleId(Integer roleId);
}
