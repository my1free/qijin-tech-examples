package tech.qijin.examples.practice.test;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tech.qijin.util4j.trace.pojo.Channel;
import tech.qijin.util4j.trace.pojo.EnvEnum;
import tech.qijin.util4j.trace.util.ChannelUtil;
import tech.qijin.util4j.trace.util.EnvUtil;
import tech.qijin.util4j.trace.util.TraceUtil;

import java.util.UUID;

/**
 * @author UnitTest
 * @date 2018/11/2
 * 开始做眼保健操：←_← ↑_↑ →_→ ↓_↓
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {})
@Transactional
@Rollback(value = false)
@ActiveProfiles(profiles = "dev")
public class BaseTest {
    protected static final Logger log = LoggerFactory.getLogger("TEST");

    @BeforeClass
    public static void beforeClass() {
        TraceUtil.setTraceId(UUID.randomUUID().toString());
        EnvUtil.setEnv(EnvEnum.TEST);
        ChannelUtil.setChannel(Channel.TEST);
    }
}
