package tech.qijin.examples.practice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ShutdownHook implements SmartLifecycle {
    @Override
    public void start() {
        log.info("ShutdownHook start");
    }

    @Override
    public void stop() {
        log.info("ShutdownHook stop");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
