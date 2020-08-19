package com.java.moudle.system.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.java.until.dba.BaseDomain;

/**
 * 菜单表(sys_user)
 * 
 */
@Entity
@Table(name = "sys_menu")
public class SysMenu extends BaseDomain {
    /** 版本号 */
    private static final long serialVersionUID = 34342401548697790L;

    /* This code was generated by TableGo tools, mark 1 begin. */

    /** 唯一标示 */
    @Id
    private String id;
    
    /** 类型(1 系统菜单 2 子菜单 3 外部链接) */
    private String type;
    
    /** 菜单路径 */
    private String url;
    
    /** 名称 */
    private String name;
    
    @Column(name = "PARENT_ID")
    private String parentId;//上级菜单内码
    
    private String icon; //图标
    
    /** 菜单等级 */
    @Column(name = "MENU_LEVEL")
    private String menuLevel;
    
    /** 排序号 */
    @Column(name = "ORDER_NUM")
    private String orderNum;
    
    private String status; //状态(1正常2冻结3删除)
    
    @Column(name = "HANDLE_TYPE")
    private String handleType;//操作类型(1添加2编辑3删除)
    

    /* This code was generated by TableGo tools, mark 1 end. */

    /* This code was generated by TableGo tools, mark 2 begin. */
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHandleType() {
		return handleType;
	}
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}
   
    /* This code was generated by TableGo tools, mark 2 end. */
}