package md.gapla.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebTokenException extends RuntimeException {

    public WebTokenException(String message) {
        super(message);
    }

}
