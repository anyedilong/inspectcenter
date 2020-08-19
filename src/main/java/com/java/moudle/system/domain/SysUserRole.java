package com.java.moudle.system.domain;
import com.java.until.dba.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户角色关联表(SYS_USER_ROLE)
 * 
 * @author zhang
 * @version 1.0.0 2020-03-17
 */
@Entity
@Table(name = "SYS_ROLE_USER")
public class SysUserRole extends BaseDomain {
    /** 版本号 */
    private static final long serialVersionUID = 7536347297524450035L;

    /** 唯一标识 */
    @Id
    @Column(name = "ID")
    private String id;

    /** 用户ID */
    @Column(name = "USER_ID")
    private String userId;

    /** 角色ID */
    @Column(name = "ROLE_ID")
    private String roleId;

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
     * 获取用户ID
     * 
     * @return 用户ID
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置用户ID
     * 
     * @param userId
     *          用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取角色ID
     * 
     * @return 角色ID
     */
    public String getRoleId() {
        return this.roleId;
    }

    /**
     * 设置角色ID
     * 
     * @param roleId
     *          角色ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}