package com.java.moudle.system.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.java.until.dba.BaseDomain;

/**
 * 角色表(SYS_ROLE)
 * 
 * @author zhang
 * @version 1.0.0 2020-03-17
 */
@Entity
@Table(name = "SYS_ROLE")
public class SysRole extends BaseDomain {
    /** 版本号 */
    private static final long serialVersionUID = -7029644935479556599L;

    /** 唯一标识 */
    @Id
    @Column(name = "ID")
    private String id;

    /** 角色CODE */
    @Column(name = "ROLE_CODE")
    private String roleCode;

    /** 角色名称 */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /** 状态  1.正常 2.冻结 3.删除 */
    @Column(name = "STATUS")
    private String status;
    
    /** 机构标识 */
    @Column(name = "ORG_ID")
    private String orgId;

    /** 角色类型  1.管理员 99.超级管理员 */
    @Column(name = "ROLE_TYPE")
    private String roleType;

    private String remark; //角色描述

    @Column(name = "create_time", updatable = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime; //创建日期

    /**
     * 获取唯一标识
     * 
     * @return 唯一标识
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置唯一标识
     * 
     * @param id
     *          唯一标识
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取角色CODE
     * 
     * @return 角色CODE
     */
    public String getRoleCode() {
        return this.roleCode;
    }

    /**
     * 设置角色CODE
     * 
     * @param roleCode
     *          角色CODE
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取角色名称
     * 
     * @return 角色名称
     */
    public String getRoleName() {
        return this.roleName;
    }

    /**
     * 设置角色名称
     * 
     * @param roleName
     *          角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取状态  1.正常 2.冻结 3.删除
     * 
     * @return 状态(0
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 设置状态  1.正常 2.冻结 3.删除
     * 
     * @param status
     *          状态(0
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRoleType() {
        return this.roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
