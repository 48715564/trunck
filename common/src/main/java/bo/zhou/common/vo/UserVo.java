package bo.zhou.common.vo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhoubo
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVo implements Serializable {
    private static final long serialVersionUID = 3881610071550902762L;

    private Integer id;

    private String avatar;

    private String username;

//    @JsonIgnore
    private String password;

    private String salt;

    private String name;

    private Date birthday;

    private Integer sex;

    private String email;

    private String phone;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
