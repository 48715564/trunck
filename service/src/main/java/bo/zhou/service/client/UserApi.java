package bo.zhou.service.client;

import bo.zhou.common.client.AuthorizedFeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@AuthorizedFeignClient(name = "uaa")
@RequestMapping("/api")
public interface UserApi {
    @RequestMapping("/user")
    Principal user(Principal user);
    @RequestMapping("/test")
    String test();
}
