package org.lab.arm.app.support;

public class TransactionTask<TREQ, TRESP> {
    private TREQ request;
    private TRESP response;

    public TransactionTask() {
    }

    public TREQ getRequest() {
        return request;
    }

    public void setRequest(TREQ request) {
        this.request = request;
    }

    public TRESP getResponse() {
        return response;
    }

    public void setResponse(TRESP response) {
        this.response = response;
    }
}
