package dxc.microservice.quarkus.application.service.exceptions;

public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String format, Object... objects) {
        super(String.format(format, objects));
    }

}
