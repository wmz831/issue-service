package io.choerodon.issue.infra.enums;

/**
 * @author shinan.chen
 * @date 2018/10/24
 */
public class SchemeApplyType {
    private SchemeApplyType() {
    }

    /**
     * 敏捷方案
     */
    public static final String AGILE = "agile";
    /**
     * 测试方案
     */
    public static final String TEST = "test";
    /**
     * 运维方案
     */
    public static final String CLOOPM = "cloopm";

    /**
     * 自定义
     */
    public static final String COMMON = "common";
}
