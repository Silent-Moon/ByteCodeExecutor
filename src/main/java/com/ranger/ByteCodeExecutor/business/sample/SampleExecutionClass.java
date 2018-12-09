package com.ranger.ByteCodeExecutor.business.sample;

import com.ranger.ByteCodeExecutor.business.ExecutionInterface;

public class SampleExecutionClass implements ExecutionInterface {
    private Thread workerThread;

    @Override
    public Object[] prepareContext() {
        return new String[]{"com.ranger.ByteCodeExecutor.business.ExecutionInterface"};
    }

    @Override
    public Object execute(Object[] context) {
        String className = (String) context[0];
        workerThread = new Thread(() -> {
            while (true) {
                System.out.println("Thread is running");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Thread is interrupted");
                    break;
                }
            }
        });
        workerThread.start();
        return "Invocation of instance of " + className + " succeeded";
    }

    @Override
    public Object stop(Object[] context) {
        String className = (String) context[0];
        // Cause workerThread to raise InterruptedException
        workerThread.interrupt();
        return "Job of instance of " + className + " is stopped";
    }
}
