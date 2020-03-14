package tech.qijin.examples.practice.server.vo;

import lombok.Data;
import lombok.ToString;
import tech.qijin.util4j.web.validation.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author michealyang
 * @date 2019-11-11
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
@ToString
public class TestVo {
    @Min(10)
    private Integer id;
    @Mobile
    private String mobile;
    @Date
    private String start;
    @Lng
    private String lng;
    @Lat
    private String lat;
    @IPV4
    private String ipv4;
}
