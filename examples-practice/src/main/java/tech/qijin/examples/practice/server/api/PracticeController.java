package tech.qijin.examples.practice.server.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.qijin.examples.practice.server.vo.TestVo;

import javax.validation.Valid;

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
@RequestMapping("/p")
public class PracticeController {
    @ResponseBody
    @GetMapping("/valid")
    public Object validation(@Valid TestVo testVo) {
        return testVo;
    }
}
