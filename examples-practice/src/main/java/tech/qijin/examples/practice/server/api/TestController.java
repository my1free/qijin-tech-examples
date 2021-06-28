package tech.qijin.examples.practice.server.api;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("exception")
    public String exception() {
        List<String> hehe = Lists.newArrayList();
        hehe.add("1");
        hehe.add("2");
        hehe.parallelStream().forEach(a -> {
            throw new IllegalStateException("dsfa");
        });
        return "Yes";
    }
}
