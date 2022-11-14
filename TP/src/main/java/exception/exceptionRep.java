package exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class exceptionRep extends Exception{
    private String message;

    public exceptionRep(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }
}
