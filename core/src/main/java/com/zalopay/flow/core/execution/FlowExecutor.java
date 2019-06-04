package com.zalopay.flow.core.execution;

import com.zalopay.flow.core.orchestrator.OrchestratorSaga;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class FlowExecutor implements Executor {
    private OrchestratorSaga orchestrator;

    @Override
    public ExecutionResult execute() {
        orchestrator.startOrchestrator();
        return null;
    }
}
