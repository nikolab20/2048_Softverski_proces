/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request;

import domain.DomainObject;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author nikolab
 */
public class Request implements Serializable {

    private RequestOperation operation;
    private DomainObject data;

    public Request() {
    }

    public Request(RequestOperation operation, DomainObject data) {
        this.operation = operation;
        this.data = data;
    }

    public RequestOperation getOperation() {
        return operation;
    }

    public void setOperation(RequestOperation operation) {
        this.operation = operation;
    }

    public DomainObject getData() {
        return data;
    }

    public void setData(DomainObject data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.operation);
        hash = 59 * hash + Objects.hashCode(this.data);
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
        final Request other = (Request) obj;
        if (this.operation != other.operation) {
            return false;
        }
        return Objects.equals(this.data, other.data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{operation=").append(operation);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

}
