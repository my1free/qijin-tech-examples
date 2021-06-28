package tech.qijin.examples.practice.test;

import lombok.Data;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
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

    enum AppOS {
        NULL,
        WEB,
        // 即将淘汰的
        NATIVE_IOS,
        // 未来之星
        IOS,
        // 未来之星
        Android;
    }
    @Data
    class App{
        private Integer appId;
        private String name;
        private AppOS os;
    }

    @Test
    public void test() {
        String expressionStr = "app.os == 'WEB'";
        ExpressionParser parser = new SpelExpressionParser(); //SpelExpressionParser是Spring内部对ExpressionParser的唯一最终实现类
        App appDto = new App();
        appDto.setName("asdfafds");

        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("app", appDto);

        Expression exp = parser.parseExpression(expressionStr); //把该表达式，解析成一个Expression对象：SpelExpression
        Boolean value = parser.parseExpression("(#app.name=='asdfafds')|| (1 > 2)").getValue(context, Boolean.class);
        System.out.println(value);



    }
}
