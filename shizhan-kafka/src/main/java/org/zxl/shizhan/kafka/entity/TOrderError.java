package org.zxl.shizhan.kafka.entity;


public class TOrderError {
  private long errorId;
  private String orderName;
  private String orderNum;
  private java.sql.Timestamp createDate;
  private java.sql.Timestamp updateDate;
  private long errorType;
  private long statusCd;
  private String orderValue;

  public long getErrorId() {
    return errorId;
  }

  public void setErrorId(long errorId) {
    this.errorId = errorId;
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


  public long getErrorType() {
    return errorType;
  }

  public void setErrorType(long errorType) {
    this.errorType = errorType;
  }


  public long getStatusCd() {
    return statusCd;
  }

  public void setStatusCd(long statusCd) {
    this.statusCd = statusCd;
  }


  public String getOrderValue() {
    return orderValue;
  }

  public void setOrderValue(String orderValue) {
    this.orderValue = orderValue;
  }

}
