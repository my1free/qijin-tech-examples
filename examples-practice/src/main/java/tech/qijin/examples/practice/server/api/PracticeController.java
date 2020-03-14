package tech.qijin.examples.practice.server.api;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.qijin.examples.practice.server.vo.TestVo;
import tech.qijin.sdk.tencent.cloud.TxCosService;
import tech.qijin.util4j.config.config.ConfigProperties;

import javax.validation.Valid;

import static tech.qijin.sdk.tencent.cloud.pojo.TxCosType.IMG;

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
public class PracticeController {
    @Autowired
    private ConfigProperties properties;
    @Autowired
    private TxCosService txCosService;
    private static final Logger aLog = LoggerFactory.getLogger("ACCESS");

    @GetMapping("/valid")
    public Object validation(@Valid TestVo testVo) {
        return testVo;
    }

    @GetMapping("/env")
    public String env() {
        return properties.getHost();
    }

    @GetMapping("/tencent/tst")
    public Object txTst() {
        aLog.info("access info");
        aLog.error("access error");
        log.info("info");
        log.debug("debug");
        log.error("error");
        return txCosService.getCredential(IMG);
    }
}
