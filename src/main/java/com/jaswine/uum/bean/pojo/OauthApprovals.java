package com.jaswine.uum.bean.pojo;


public class OauthApprovals {

  private String userid;
  private String clientid;
  private String scope;
  private String status;
  private java.sql.Timestamp expiresat;
  private java.sql.Timestamp lastmodifiedat;


  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }


  public String getClientid() {
    return clientid;
  }

  public void setClientid(String clientid) {
    this.clientid = clientid;
  }


  public String getScope() {
    return scope;
  }

  public void setScope(String scope) {
    this.scope = scope;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public java.sql.Timestamp getExpiresat() {
    return expiresat;
  }

  public void setExpiresat(java.sql.Timestamp expiresat) {
    this.expiresat = expiresat;
  }


  public java.sql.Timestamp getLastmodifiedat() {
    return lastmodifiedat;
  }

  public void setLastmodifiedat(java.sql.Timestamp lastmodifiedat) {
    this.lastmodifiedat = lastmodifiedat;
  }

}
