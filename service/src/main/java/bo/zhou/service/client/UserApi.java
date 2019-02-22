package bo.zhou.service.client;

import bo.zhou.common.client.AuthorizedFeignClient;
import bo.zhou.common.client.AuthorizedUserFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@AuthorizedFeignClient(name = "uaa")
@RequestMapping("/api")
public interface UserApi {
    @RequestMapping("/user")
    public Principal user(Principal user);
    @RequestMapping("/test")
    public String test();
}
