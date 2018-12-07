package bo.zhou.user.service;


import bo.zhou.user.entity.RcUser;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Mr.Yangxiufeng
 * Date: 2018-05-09
 * Time: 9:48
 */
public interface UserService {
    RcUser findByUsername(String username);
}
