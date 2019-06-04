package com.zalopay.flow.service;

import com.zalopay.flow.core.execution.Executor;
import com.zalopay.flow.core.model.Flow;
import com.zalopay.flow.core.execution.FlowExecutor;
import com.zalopay.flow.core.model.TaskExecution;
import com.zalopay.flow.core.orchestrator.Orchestrator;
import com.zalopay.flow.core.orchestrator.OrchestratorSaga;
import com.zalopay.flow.core.task.Task;
import com.zalopay.flow.repository.FlowRepository;
import com.zalopay.flow.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface FlowService {
    String createFlow(Flow f);

    String createTask(Task t);

    void runFlow(String flowId);
}

@Service
class FlowServiceImpl implements FlowService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    FlowRepository flowRepository;

    @Override
    public String createFlow(Flow flow) {
        Flow f = flowRepository.insert(flow);
        return f.getName();
    }

    @Override
    public String createTask(Task task) {
        Task t = taskRepository.insert(task);
        return t.getName();
    }

    @Override
    public void runFlow(String flowId) {
        Optional<Flow> flow = flowRepository.findById(flowId);

        List<TaskExecution> list = flow.get().getTasks();

        OrchestratorSaga oc = new Orchestrator(flowId, list);
        Executor flowExecutor = FlowExecutor.builder().orchestrator(oc).build();
        flowExecutor.execute();
    }
}