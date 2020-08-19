package com.java.moudle.system.dto;

import com.java.until.dba.BaseDomain;
import com.java.until.dba.PageModel;
import com.java.until.validate.Validate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author zhang
 * @version 1.0.0 2020-03-17
 */
public class SysRoleDto implements Serializable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = -7029644935479556598L;

    /**
     * 唯一标识
     */
    private String id;

    /**
     * 角色CODE
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    private String remark; //角色描述


    /**
     * 状态(0.正常 1.删除)
     */
    private String status;

    /**
     * 角色类型  1 本科室 2 本人 (3和99不参与角色分配)
     */
    private String roleType;

    /**
     * 角色id
     */
    private String userId;

    private List<SysMenuDto> menuList;

    private Integer pageNo;
    private Integer pageSize;
    private PageModel page;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


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
     * @param id 唯一标识
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
     * @param roleCode 角色CODE
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
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取状态(0.正常 1.删除)
     *
     * @return 状态(0
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 设置状态(0.正常 1.删除)
     *
     * @param status 状态(0
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取角色类型  1 本科室 2 本人 (3和99不参与角色分配)
     *
     * @return 角色类型  1 本科室 2 本人 (3和99不参与角色分配)
     */
    public String getRoleType() {
        return this.roleType;
    }

    /**
     * 设置角色类型  1 本科室 2 本人 (3和99不参与角色分配)
     *
     * @param roleType 角色类型  1 本科室 2 本人 (3和99不参与角色分配)
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public List<SysMenuDto> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenuDto> menuList) {
        this.menuList = menuList;
    }

    public PageModel getPage() {
        return page;
    }

    public void setPage(PageModel page) {
        this.page = page;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
