package org.camunda.bpm.getstarted.loanapproval;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("assignment")
public class AssignmentService {
    @Autowired
    private RuntimeService runtimeService;

    public String getCandidateGroups(String taskKey) {
        ProcessEngine defaultProcessEngine = BpmPlatform.getDefaultProcessEngine();
        System.out.println(BpmPlatform.getProcessEngineService().getProcessEngineNames());
        DecisionService decisionService = defaultProcessEngine.getDecisionService();
        Map<String, Object> variables = Map.of("taskKey",taskKey,"caseType","default");
        DmnDecisionTableResult
                dishDecisionResult = decisionService.evaluateDecisionTableByKey("Decision_1bbyrh9", variables);
        String group = dishDecisionResult.getSingleEntry();
        return group;
    }
}
