package bo.zhou.common.exception;

import bo.zhou.common.vo.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

@Slf4j
public class Auth2ResponseExceptionTranslator implements WebResponseExceptionTranslator {
    private final String LOGIN_ERROR_INFO = "Bad credentials";
    private final String IMPLICIT_GRANT_TYPE_NOT_SUPPORTED = "Implicit grant type not supported from token endpoint";

    @Override
    public ResponseEntity<CustomOauthException> translate(Exception e) {
        log.error(e.getMessage());
        if (e instanceof InvalidTokenException) {
            return ResponseEntity.ok(new CustomOauthException("token过期或无效", ((InvalidTokenException) e).getHttpErrorCode()));
        } else if (e instanceof InsufficientAuthenticationException) {
            return ResponseEntity.ok(new CustomOauthException("没有对应的身份信息", ResponseCode.UNAUTHORIZED));
        } else if (e instanceof InvalidRequestException) {
            return ResponseEntity.ok(new CustomOauthException("没有授权类型字段", ((InvalidRequestException) e).getHttpErrorCode()));
        } else if (e instanceof InvalidGrantException) {
            if (LOGIN_ERROR_INFO.equals(e.getMessage())) {
                return ResponseEntity.ok(new CustomOauthException("用户名或者密码错误", ((InvalidGrantException) e).getHttpErrorCode()));
            } else if (IMPLICIT_GRANT_TYPE_NOT_SUPPORTED.equals(e.getMessage())) {
                return ResponseEntity.ok(new CustomOauthException("不支持的授权类型", ((InvalidGrantException) e).getHttpErrorCode()));
            }
        } else if (e instanceof UnsupportedGrantTypeException) {
            return ResponseEntity.ok(new CustomOauthException("不支持的授权类型", ((UnsupportedGrantTypeException) e).getHttpErrorCode()));
        }
        return ResponseEntity.ok(new CustomOauthException(e.getMessage()));
    }
}