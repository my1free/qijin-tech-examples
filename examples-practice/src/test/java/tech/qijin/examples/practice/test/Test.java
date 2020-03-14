package tech.qijin.examples.practice.test;

import com.google.common.hash.Hashing;
import com.googlecode.aviator.AviatorEvaluator;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import tech.qijin.util4j.utils.DateUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author michealyang
 * @date 2019-11-06
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
public class Test {
    @org.junit.Test
    public void consistentHash() throws IOException {
        long l = DateUtil.now().getTime() / 1000;
        System.out.print(l);
        System.out.println((int) l);
        System.out.println(2^2);
    }

    @org.junit.Test
    public void aviator() {
        String name = "唐简";
        Map<String, Object> env = new HashMap<>();
        env.put("name", name);
        env.put("a", 2);
        Object result = AviatorEvaluator.execute("a > 1", env);
        System.out.println(result);
    }

    @org.junit.Test
    public void jsqlParser() throws JSQLParserException {
        Statement stmt = CCJSqlParserUtil.parse("SELECT * FROM tab1");
        log.info("{}", stmt);
    }
}
