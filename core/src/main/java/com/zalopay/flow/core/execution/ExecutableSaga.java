package com.zalopay.flow.core.execution;

public interface ExecutableSaga {
    ExecutionResult run();
    ExecutionResult compensate();
    String status();
}
