package bo.zhou.common.endpoint;

import bo.zhou.common.vo.ResponseCode;
import bo.zhou.common.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhoubo
 */
@Slf4j
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final String FULL_AUTHENTICATION_REQUIRED = "Full authentication is required to access this resource";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Spring Securtiy异常:{}", authException.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        String msg = "";
        if(FULL_AUTHENTICATION_REQUIRED.equals(authException.getMessage())){
            msg = "token不能为空";
        }else{
            msg = authException.getMessage();
        }
        Result result = new Result();
        result.setMsg(msg);
        result.setCode(ResponseCode.UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}