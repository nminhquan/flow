package com.zalopay.flow.core.orchestrator;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.zalopay.flow.core.execution.TaskExecutor;
import com.zalopay.flow.core.model.TaskExecution;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Orchestrator implements OrchestratorSaga {
    private String actorSystemName;
    private List<TaskExecution> taskList;
    ActorSystem actorSystem;

    public Orchestrator(String actorSystemName, List<TaskExecution> list) {
        this.taskList = list;
        this.actorSystemName = actorSystemName;
        actorSystem = ActorSystem.create(this.actorSystemName);
    }

    @Override
    public void startOrchestrator() {
        // actor system will call for actor
        // receive the return from actor sagas and proceed the next step

        // if parallel
        for (TaskExecution task : taskList) {
            ActorRef ref = actorSystem.actorOf(Props.create(TaskExecutor.class), task.toString());
            ref.tell("", ActorRef.noSender());
        }

    }

    @Override
    public void stopOrchestrator() {
        actorSystem.terminate();
    }


    @Override
    public String toString() {
        return "actor=" + actorSystem.toString();
    }

}
