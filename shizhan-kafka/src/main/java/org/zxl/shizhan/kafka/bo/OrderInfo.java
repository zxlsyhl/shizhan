package org.zxl.shizhan.kafka.bo;

import org.zxl.shizhan.kafka.entity.TOrder;
import org.zxl.shizhan.kafka.entity.TOrderItem;

import java.util.List;

public class OrderInfo {
    private TOrder tOrder;
    private List<TOrderItem> tOrderItems;

    public TOrder gettOrder() {
        return tOrder;
    }

    public void settOrder(TOrder tOrder) {
        this.tOrder = tOrder;
    }

    public List<TOrderItem> gettOrderItems() {
        return tOrderItems;
    }

    public void settOrderItems(List<TOrderItem> tOrderItems) {
        this.tOrderItems = tOrderItems;
    }
}
