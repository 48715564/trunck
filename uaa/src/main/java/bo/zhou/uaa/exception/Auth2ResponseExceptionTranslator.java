package bo.zhou.uaa.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

@Slf4j
public class Auth2ResponseExceptionTranslator implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity<CustomOauthException> translate(Exception e) {
        log.error("Auth2异常", e);
        Throwable throwable = e.getCause();
        if (throwable instanceof InvalidTokenException) {
            log.info("token失效:{}", throwable);
            return ResponseEntity.ok(new CustomOauthException(e.getMessage()));
        }
        return ResponseEntity.ok(new CustomOauthException(e.getMessage()));
    }
}