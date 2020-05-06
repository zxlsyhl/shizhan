package org.zxl.shizhan.kafka.tool;

public enum OrderSd {
    CREATE(1, "新建"),
    RUNNING(2,"在途"),
    DONE(3, "完工"),
    CACEL(4, "撤单");
    private final long sd;
    private final String desc;

    public long getSd() {
        return sd;
    }

    public String getDesc() {
        return desc;
    }

    OrderSd(long sd, String desc) {
        this.sd = sd;
        this.desc = desc;
    }
}
