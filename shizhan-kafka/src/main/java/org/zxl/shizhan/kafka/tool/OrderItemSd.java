package org.zxl.shizhan.kafka.tool;

public enum OrderItemSd {
    CREATE(1, "新建"),
    RUNNING(2,"在途"),
    DONE(3, "完工"),
    CACEL(4, "撤单");
    private final int sd;
    private final String desc;

    public int getSd() {
        return sd;
    }

    public String getDesc() {
        return desc;
    }

    OrderItemSd(int sd, String desc) {
        this.sd = sd;
        this.desc = desc;
    }
}
