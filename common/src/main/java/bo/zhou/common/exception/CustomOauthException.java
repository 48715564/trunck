package bo.zhou.common.exception;
import bo.zhou.common.serializer.CustomOauthExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author zhoubo
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    @Override
    public int getHttpErrorCode() {
        return httpErrorCode;
    }

    public void setHttpErrorCode(int httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }

    private int httpErrorCode;

    public CustomOauthException(String msg) {
        super(msg);
        this.httpErrorCode = super.getHttpErrorCode();
    }

    public CustomOauthException(String msg,int httpErrorCode) {
        super(msg);
        this.httpErrorCode = httpErrorCode;
    }
}