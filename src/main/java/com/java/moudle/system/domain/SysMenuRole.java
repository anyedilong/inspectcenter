package com.java.moudle.system.domain;
import com.java.until.dba.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色功能关联表(SYS_ROLE_FUNCTION)
 * 
 * @author zhang
 * @version 1.0.0 2020-03-17
 */
@Entity
@Table(name = "SYS_MENU_ROLE")
public class SysMenuRole extends BaseDomain {
    /** 版本号 */
    private static final long serialVersionUID = -3442079806438608902L;

    /** 唯一标识 */
    @Id
    @Column(name = "ID")
    private String id;

    /** 角色id */
    @Column(name = "ROLE_ID")
    private String roleId;

    /** 功能id */
    @Column(name = "MENU_ID")
    private String menuId;

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
     * 获取角色id
     * 
     * @return 角色id
     */
    public String getRoleId() {
        return this.roleId;
    }

    /**
     * 设置角色id
     * 
     * @param roleId
     *          角色id
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}