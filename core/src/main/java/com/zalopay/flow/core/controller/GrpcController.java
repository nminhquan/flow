package com.zalopay.flow.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zalopay.flow.ZPFlowBaseRequest;
import com.zalopay.flow.ZPFlowBaseResponse;
import com.zalopay.flow.ZPFlowGrpc;
import com.zalopay.flow.core.model.Flow;
import com.zalopay.flow.core.model.RunFlowRequest;
import com.zalopay.flow.core.task.Task;
import com.zalopay.flow.service.FlowService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by thinhda.
 * Date: 2019-05-21
 */

@GRpcService
public class GrpcController extends ZPFlowGrpc.ZPFlowImplBase {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    FlowService flowService;

    @Override
    public void processRequest(ZPFlowBaseRequest request, StreamObserver<ZPFlowBaseResponse> responseObserver) {
        String response = null;
        try {
            switch(request.getMethodName()) {
                case MethodName.CREATE_FLOW:
                    Flow flow = mapper.readValue(request.getParamsJson(), Flow.class);
                    response = flowService.createFlow(flow);
                    break;
                case MethodName.CREATE_TASK:
                    Task task = mapper.readValue(request.getParamsJson(), Task.class);
                    response =  flowService.createTask(task);
                    break;
                case MethodName.RUN_FLOW:
                    RunFlowRequest runRequest = mapper.readValue(request.getParamsJson(), RunFlowRequest.class);
                    flowService.runFlow(runRequest.getFlowId());
                    break;
                default:
                    response = "default";

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

//        responseObserver.onNext(ZPFlowBaseResponse.newBuilder()
//                .setResponse(response)
//                .build());
//        responseObserver.onCompleted();

    }

}