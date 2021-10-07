package coinbase.cloud.java.examples.quickstart.utils;

/**
 * Program to manage custom errors.
 *
 * @author Suren K
 * @since 10-05-2021
 */
public class RequestError {
    private String message;
    private int code;

    public RequestError(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RequestError{" +
                "message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}