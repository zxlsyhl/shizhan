package org.zxl.shizhan.kafka.tool;

public enum ErrorType {
    CHECKOUT(1, "校验不通过"),
    WEBERR(2, "网络异常");

    private final long type;
    private final String desc;

    ErrorType(long type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public long getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
