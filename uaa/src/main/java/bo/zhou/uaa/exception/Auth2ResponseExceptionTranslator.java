package bo.zhou.uaa.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

@Slf4j
public class Auth2ResponseExceptionTranslator implements WebResponseExceptionTranslator {
    private final String LOGIN_ERROR_INFO="Bad credentials";
    @Override
    public ResponseEntity<CustomOauthException> translate(Exception e) {
        log.error("Auth2异常", e);
        Throwable throwable = e.getCause();
        if (throwable instanceof InvalidTokenException) {
            log.info("token失效:{}", throwable);
            return ResponseEntity.ok(new CustomOauthException("token过期或无效",401));
        }
        if(LOGIN_ERROR_INFO.equals(e.getMessage())){
            return ResponseEntity.ok(new CustomOauthException("用户名或者密码错误"));
        }
        return ResponseEntity.ok(new CustomOauthException(e.getMessage()));
    }
}