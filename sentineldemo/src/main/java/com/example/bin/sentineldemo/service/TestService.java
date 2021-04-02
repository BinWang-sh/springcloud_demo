package com.example.bin.sentineldemo.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    //If get config from nacos, this config rule will be replaced.
    @PostConstruct
    private void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置QPS为1
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    @SentinelResource(value = "sayHello-resource",blockHandler="sayHelloBlockMethod")
    public String sayHello(String name) {
        List<FlowRule> rules = FlowRuleManager.getRules();
        return "Hello, " + name;
    }

    /**
     * 降级方法，限流后应用
     * @return
     */
    public String sayHelloBlockMethod(String name, BlockException blockException){

        return "请求被限流,触发限流规则="+blockException.getRule().getResource();
    }

}