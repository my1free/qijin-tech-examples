package tech.qijin.examples.practice.server.api;

import com.google.common.collect.Maps;
import com.sun.management.HotSpotDiagnosticMXBean;
import com.sun.management.VMOption;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.qijin.examples.practice.server.vo.TestVo;
import tech.qijin.examples.practice.service.MxBeanService;
import tech.qijin.sdk.tencent.cloud.TxCosService;
import tech.qijin.util4j.config.config.ConfigProperties;

import javax.validation.Valid;

import java.lang.management.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

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
@RequestMapping("/mxbeans")
public class MxBeansController {
    @Autowired
    private ConfigProperties properties;

    @Autowired
    private MxBeanService mxBeanService;

    @GetMapping("/info")
    public Object info() {
        return mxBeanService.info();
    }

    @GetMapping("/vmoption")
    public Object vmoption() {
        HotSpotDiagnosticMXBean hotSpotDiagnosticMXBean = ManagementFactory
                .getPlatformMXBean(HotSpotDiagnosticMXBean.class);
        return hotSpotDiagnosticMXBean.getDiagnosticOptions();
    }
}
