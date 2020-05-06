package org.zxl.shizhan.kafka.dao;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.zxl.shizhan.kafka.entity.TOrder;
import org.zxl.shizhan.kafka.entity.TOrderError;
import org.zxl.shizhan.kafka.entity.TOrderItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
public class OrderDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertOrder(TOrder tOrder , List<TOrderItem> orderItems){
        int result = 0;
        Connection connection = null;
        try {
            connection = jdbcTemplate.getDataSource().getConnection();
            connection.setAutoCommit(false);
            jdbcTemplate.update("insert into t_order(order_id, order_name, order_num, status_cd) values(?,?,?,?,?,?)",
                    new Object[]{tOrder.getOrderId(),tOrder.getOrderName(),tOrder.getOrderNum(),tOrder.getStatusCd()});
            for (TOrderItem item:orderItems
                 ) {
                jdbcTemplate.update("insert into t_order_item(order_item_id, order_id, order_item_type, status_cd) values (?,?,?,?,?,?)",
                        new Object[]{item.getOrderItemId(),item.getOrderId(),item.getOrderItemType(),item.getStatusCd()});
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            result = -1;
        } finally {
            if(connection!=null){
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }return result;
    }

    public int insertOrderError(TOrderError tOrderError){
        int result = jdbcTemplate.update("insert into t_order_error(error_id, order_num, order_name, error_type, status_cd, order_value) values(?,?,?,?,?,?,?,?)",
                new Object[]{tOrderError.getErrorId(),tOrderError.getOrderNum(),tOrderError.getOrderName(),
                        tOrderError.getErrorType(),tOrderError.getStatusCd(),tOrderError.getOrderValue()});
        return result;
    }

}
