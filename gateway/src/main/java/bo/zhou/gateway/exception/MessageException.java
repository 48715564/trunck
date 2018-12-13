package bo.zhou.gateway.exception;

/**
 * @author zhoubo
 */
public class MessageException extends RuntimeException{
    private Exception exception;
    private String message;

    public MessageException(String message, Exception exception){
        this.exception = exception;
        this.message = message;
    }


    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
