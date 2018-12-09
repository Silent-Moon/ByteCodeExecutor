package com.ranger.ByteCodeExecutor.response;

public class ResponseObject {
    private boolean successful;
    private Object executionResult;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public Object getExecutionResult() {
        return executionResult;
    }

    public void setExecutionResult(Object executionResult) {
        this.executionResult = executionResult;
    }
}
