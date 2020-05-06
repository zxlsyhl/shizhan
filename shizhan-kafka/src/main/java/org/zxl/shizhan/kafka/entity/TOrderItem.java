package org.zxl.shizhan.kafka.entity;


public class TOrderItem {

  private long orderItemId;
  private long orderId;
  private long orderItemType;
  private long statusCd;
  private java.sql.Timestamp createDate;
  private java.sql.Timestamp updateDate;


  public long getOrderItemId() {
    return orderItemId;
  }

  public void setOrderItemId(long orderItemId) {
    this.orderItemId = orderItemId;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public long getOrderItemType() {
    return orderItemType;
  }

  public void setOrderItemType(long orderItemType) {
    this.orderItemType = orderItemType;
  }


  public long getStatusCd() {
    return statusCd;
  }

  public void setStatusCd(long statusCd) {
    this.statusCd = statusCd;
  }


  public java.sql.Timestamp getCreateDate() {
    return createDate;
  }

  public void setCreateDate(java.sql.Timestamp createDate) {
    this.createDate = createDate;
  }


  public java.sql.Timestamp getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(java.sql.Timestamp updateDate) {
    this.updateDate = updateDate;
  }

}
