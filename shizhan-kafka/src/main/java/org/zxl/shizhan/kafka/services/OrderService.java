package org.zxl.shizhan.kafka.services;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zxl.shizhan.kafka.bo.OrderInfo;
import org.zxl.shizhan.kafka.dao.OrderDAO;
import org.zxl.shizhan.kafka.entity.TOrder;
import org.zxl.shizhan.kafka.entity.TOrderError;
import org.zxl.shizhan.kafka.entity.TOrderItem;
import org.zxl.shizhan.kafka.tool.ErrorType;
import org.zxl.shizhan.kafka.tool.OrderSd;
import org.zxl.shizhan.kafka.tool.SnowFlakeUtils;

import java.util.List;

@Service
public class OrderService {
    private Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderDAO orderDAO;

    private SnowFlakeUtils instance = SnowFlakeUtils.get();

    private Gson gson = new Gson();

    public int insertOrder(OrderInfo orderInfo){
        logger.debug("insertOrder begin orderInfo:{} ",gson.toJson(orderInfo));
        int insertflag = -1;
        TOrder tOrder = orderInfo.gettOrder();
        long orderId = instance.nextId();
        tOrder.setOrderId(orderId);
        tOrder.setStatusCd(OrderSd.CREATE.getSd());
        List<TOrderItem> orderItems = orderInfo.gettOrderItems();
        orderItems.stream().forEach(tOrderItem -> {tOrderItem.setOrderId(orderId); tOrderItem.setOrderItemId(instance.nextId());});
        int result = orderDAO.insertOrder(tOrder, orderItems);
        if(result == 0){
            TOrderError tOrderError = new TOrderError();
            tOrderError.setErrorId(instance.nextId());
            tOrderError.setErrorType(ErrorType.WEBERR.getType());
            tOrderError.setOrderName(tOrder.getOrderName());
            tOrderError.setOrderNum(tOrder.getOrderNum());
            tOrderError.setStatusCd(OrderSd.CREATE.getSd());
            tOrderError.setOrderValue(gson.toJson(orderInfo));
            result = orderDAO.insertOrderError(tOrderError);
            if(result!=0){
                insertflag =0;
            }
        }else{
            insertflag = 0;
        }
        logger.debug("insertOrder end insertflag:{} ",insertflag);
        return insertflag;
    }
}
