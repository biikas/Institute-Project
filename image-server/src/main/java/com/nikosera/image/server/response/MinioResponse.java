package com.nikosera.image.server.response;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
public class MinioResponse {

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

    public MinioResponse success(String message) {
        this.message = message;
        this.status = true;
        return this;
    }

    public MinioResponse failure(String message) {
        this.message = message;
        this.status = false;
        return this;
    }
}
