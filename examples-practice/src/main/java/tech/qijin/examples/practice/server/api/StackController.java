package tech.qijin.examples.practice.server.api;

import com.sun.management.HotSpotDiagnosticMXBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.qijin.examples.practice.service.MxBeanService;
import tech.qijin.examples.practice.service.StackService;
import tech.qijin.util4j.config.config.ConfigProperties;

import java.lang.management.ManagementFactory;

/**
 * 已读相关操作
 * <p>
 * 包含功能:
 * <ul>
 * <li>清除未读数</li>
 * </ul>
 * </p>
 *
 * @author michealyang
 * @date 2019-11-04
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@RestController
@RequestMapping("/stack")
public class StackController {

    @Autowired
    private StackService stackService;

    @GetMapping("/trace")
    public Object info() {
        return stackService.stack();
    }

}
