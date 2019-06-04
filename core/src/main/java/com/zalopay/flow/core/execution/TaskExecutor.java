package com.zalopay.flow.core.execution;

import akka.actor.AbstractActor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TaskExecutor extends AbstractActor implements ExecutableSaga {

    @Override
    public Receive createReceive() {
        ExecutionResult executionResult = run();
        if (executionResult.getResultCode().equals("")) {
            compensate();
        }
        return receiveBuilder().build();
    }

    @Override
    public ExecutionResult run() {
        return new ExecutionResult("OK");
    }

    @Override
    public ExecutionResult compensate() {
        return new ExecutionResult("OK");
    }

    @Override
    public String status() {
        return null;
    }
}

@Getter
@Setter
@AllArgsConstructor
class ExecutionResult {
    private String resultCode;
}
