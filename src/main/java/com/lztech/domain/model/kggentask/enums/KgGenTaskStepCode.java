package com.lztech.domain.model.kggentask.enums;

public enum KgGenTaskStepCode {

    STEP_NODE_GEN(0, "节点生成"),
    STEP_NODE_DETAIL_GEN(1, "节点详情生成"),
    STEP_RESOURCE_RELATED(2, "资源关联"),
    STEP_IMPORT_DATABASE(3, "导入");

    // 关联
    private Integer index;
    private String name;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    KgGenTaskStepCode(Integer index, String name) {
        this.index = index;
        this.name = name;
    }

    public static KgGenTaskStepCode getByIndex(Integer index) {
        for (KgGenTaskStepCode kgGenTaskStepCode : KgGenTaskStepCode.values()) {
            if (kgGenTaskStepCode.getIndex().equals(index)) {
                return kgGenTaskStepCode;
            }
        }
        return null;
    }

}
