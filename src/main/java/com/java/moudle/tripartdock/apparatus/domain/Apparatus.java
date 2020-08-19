package com.java.moudle.tripartdock.apparatus.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.java.until.dba.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

//检验仪器字典

@Entity
@Table(name = "sys_exam_apparatus")
public class Apparatus extends BaseDomain {

    @Id
    private String id;
    private String orgId;//机构ID
    private String orgCode;//机构编号
    private String orgName;//机构名称
    private String name;//仪器名称
    private String model;//仪器型号
    private String code;//仪器编号
    private String type;//仪器类型

    @JSONField(format = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
