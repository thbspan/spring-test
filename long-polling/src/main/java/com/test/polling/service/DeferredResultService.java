package com.test.polling.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.test.polling.model.DeferredResultResponse;

@Service
public class DeferredResultService {
    private Map<String, Consumer<DeferredResultResponse>> taskMap = new ConcurrentHashMap<>();

    public void process(String id, DeferredResult<DeferredResultResponse> deferredResult) {
        deferredResult.onTimeout(() -> {
            taskMap.remove(id);
            DeferredResultResponse deferredResultResponse = new DeferredResultResponse();
            DeferredResultResponse.Msg timeout = DeferredResultResponse.Msg.TIMEOUT;
            deferredResultResponse.setCode(timeout.getCode());
            deferredResultResponse.setMsg(timeout.getDesc());
            deferredResult.setResult(deferredResultResponse);
        });
        Optional.ofNullable(taskMap)
                .filter(t -> !t.containsKey(id))
                .orElseThrow(() -> new IllegalArgumentException(String.format("id=%s is existing", id)));
        taskMap.put(id, deferredResult::setResult);
    }

    public void settingResult(String id, DeferredResultResponse deferredResultResponse) {
        if (taskMap.containsKey(id)) {
            Consumer<DeferredResultResponse> deferredResultResponseConsumer = taskMap.get(id);
            deferredResultResponseConsumer.accept(deferredResultResponse);
            taskMap.remove(id);
        }
    }
}
