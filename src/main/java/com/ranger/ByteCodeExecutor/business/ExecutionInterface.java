package com.ranger.ByteCodeExecutor.business;

public interface ExecutionInterface {
    Object[] prepareContext();

    Object execute(Object[] context);

    Object stop(Object[] context);
}
