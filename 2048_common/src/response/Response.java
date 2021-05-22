/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package response;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author nikolab
 */
public class Response implements Serializable {
    
    private ResponseStatus status;
    private String message;
    private Exception exception;
    private Object payload;
    
    public Response() {
    }

    public Response(ResponseStatus status, String message, Object payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public Response(ResponseStatus status, String message, Exception exception, Object payload) {
        this.status = status;
        this.message = message;
        this.exception = exception;
        this.payload = payload;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.message);
        hash = 97 * hash + Objects.hashCode(this.exception);
        hash = 97 * hash + Objects.hashCode(this.payload);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Response other = (Response) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.exception, other.exception)) {
            return false;
        }
        return Objects.equals(this.payload, other.payload);
    }

    @Override
    public String toString() {
        return "Response{" + "status=" + status + ", message=" + message + ", exception=" + exception + ", payload=" + payload + '}';
    }
    
    
}
