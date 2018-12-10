package bo.zhou.uaa.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhoubo
 * @since 2018-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RcMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 菜单编码
     */
    private String code;

    /**
     * 菜单父编码
     */
    private String pCode;

    /**
     * 父菜单ID
     */
    private String pId;

    /**
     * 名称
     */
    private String name;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 是否是菜单
     */
    private Integer isMenu;

    /**
     * 菜单层级
     */
    private Integer level;

    /**
     * 菜单排序
     */
    private Integer sort;

    private Integer status;

    private String icon;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
