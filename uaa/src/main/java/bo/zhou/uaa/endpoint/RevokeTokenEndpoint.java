package bo.zhou.uaa.endpoint;
import bo.zhou.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Mr.Yangxiufeng on 2018/1/2.
 * Time:9:22
 * ProjectName:Mirco-Service-Skeleton
 */
@FrameworkEndpoint
public class RevokeTokenEndpoint {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping(value = "/oauth/token", method= RequestMethod.DELETE)
    public @ResponseBody
    Result revokeToken(String access_token){
        Result msg = new Result();
        if (consumerTokenServices.revokeToken(access_token)){
            msg.setCode(Result.SUCCESS);
            msg.setMsg("注销成功");
        }else {
            msg.setCode(Result.FAILED);
            msg.setMsg("注销失败");
        }
        return msg;
    }
}
