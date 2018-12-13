package bo.zhou.gateway.handler;

import bo.zhou.common.vo.ErrorCode;
import bo.zhou.common.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: lixiupeng
 * @Date: 2018/11/21 15:54
 * @Description: 自定义权限不足处理
 */
@Service
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    ObjectMapper objectMapper = new ObjectMapper();

    private String errorPage;
 
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
            //设置状态为403，无权限状态
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            //设置格式以及返回json数据 方便前台使用reponseJSON接取
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            Result result = new Result();
            result.setMsg("权限不足，请联系管理员");
            result.setCode(ErrorCode.FORBIDDEN);
            out.append(objectMapper.writeValueAsString(result));
    }
}