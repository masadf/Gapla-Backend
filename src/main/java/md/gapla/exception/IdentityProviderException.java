package md.gapla.exception;

public class IdentityProviderException extends RuntimeException {

    public IdentityProviderException() {
    }

    public IdentityProviderException(String message, Exception cause) {
        super(message, cause);
    }
}
