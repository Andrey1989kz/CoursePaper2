package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MoreQuestionsThanStoredException extends RuntimeException {

    public MoreQuestionsThanStoredException() {
    }

    public MoreQuestionsThanStoredException(String message) {
        super(message);
    }

    public MoreQuestionsThanStoredException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoreQuestionsThanStoredException(Throwable cause) {
        super(cause);
    }

    public MoreQuestionsThanStoredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

