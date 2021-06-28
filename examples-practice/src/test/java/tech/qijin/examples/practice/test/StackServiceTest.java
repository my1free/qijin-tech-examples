package tech.qijin.examples.practice.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.examples.practice.service.StackService;

@Slf4j
public class StackServiceTest extends BaseTest{
    @Autowired
    private StackService stackService;

    @Test
    public void testStack() {
        stackService.stack();
    }
}
