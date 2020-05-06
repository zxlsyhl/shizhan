package org.zxl.shizhan.kafka.tool;

public enum ErrorSd {
    WAIT(1, "待处理"),
    DONE(2, "已处理");

    private final long sd;
    private final String desc;

    public long getSd() {
        return sd;
    }

    public String getDesc() {
        return desc;
    }

    ErrorSd(long sd, String desc) {
        this.sd = sd;
        this.desc = desc;
    }
}
