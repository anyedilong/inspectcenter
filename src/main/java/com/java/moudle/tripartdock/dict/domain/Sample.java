package com.java.moudle.tripartdock.dict.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.java.until.dba.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

//标本类型字典

@Entity
@Table(name = "sys_exam_sample")
public class Sample extends BaseDomain {

    @Id
    private String id;
    private String name;//标本类型名称
    private String spell;//拼音缩写
    @JSONField(format = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date createDate;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
