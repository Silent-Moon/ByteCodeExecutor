package com.ranger.ByteCodeExecutor.service;

import com.ranger.ByteCodeExecutor.business.ByteCodeServiceLoader;
import com.ranger.ByteCodeExecutor.business.ExecutionInterface;
import com.ranger.ByteCodeExecutor.response.ResponseObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ByteCodeService {
    private Map<String, Object> instancesMap = new HashMap<>();

    public ResponseObject executeByteCode(byte[] classBytes, boolean isExecute, String uuid) {
        if (isExecute) {
            ByteCodeServiceLoader byteCodeServiceLoader = new ByteCodeServiceLoader();
            Class clazz = byteCodeServiceLoader.loadBytes(classBytes);
            return execute(clazz, uuid);
        } else {
            return stop(uuid);
        }
    }

    private ResponseObject execute(Class clazz, String uuid) {
        ResponseObject executionResultDTO = new ResponseObject();
        try {
            ExecutionInterface instance = (ExecutionInterface) clazz.newInstance();
            Object[] context = instance.prepareContext();
            Object executionResult = instance.execute(context);
            if (!instancesMap.containsKey(uuid)) {
                instancesMap.put(uuid, instance);
            }
            executionResultDTO.setExecutionResult(executionResult);
            executionResultDTO.setSuccessful(true);
        } catch (IllegalAccessException | InstantiationException e) {
            executionResultDTO.setSuccessful(false);
        }
        return executionResultDTO;
    }

    private ResponseObject stop(String uuid) {
        ResponseObject executionResultDTO = new ResponseObject();
        if (instancesMap.containsKey(uuid)) {
            ExecutionInterface instance = (ExecutionInterface) instancesMap.get(uuid);
            Object[] context = instance.prepareContext();
            Object executionResult = instance.stop(context);
            executionResultDTO.setSuccessful(true);
            executionResultDTO.setExecutionResult(executionResult);
        } else {
            executionResultDTO.setSuccessful(false);
        }
        return executionResultDTO;
    }
}
