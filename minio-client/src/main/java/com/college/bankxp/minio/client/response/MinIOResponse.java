package com.college.bankxp.minio.client.response;

/**
 *
 * @author b.nox<binod.bhandari@college.com>
 */
public class MinIOResponse {

    private String message;
    private boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public MinIOResponse success(String messae) {
        this.message = messae;
        this.status = true;
        return this;
    }
    public MinIOResponse failure(String messae) {
        this.message = messae;
        this.status = false;
        return this;
    }

}
