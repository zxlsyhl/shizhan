package org.zxl.shizhan.kafka.entity;


public class TOrder {

  private long orderId;
  private String orderName;
  private String orderNum;
  private java.sql.Timestamp createDate;
  private java.sql.Timestamp updateDate;
  private long statusCd;


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public String getOrderName() {
    return orderName;
  }

  public void setOrderName(String orderName) {
    this.orderName = orderName;
  }


  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
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


  public long getStatusCd() {
    return statusCd;
  }

  public void setStatusCd(long statusCd) {
    this.statusCd = statusCd;
  }

}
