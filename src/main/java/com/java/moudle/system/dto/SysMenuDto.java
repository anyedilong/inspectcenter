package com.java.moudle.system.dto;


import com.java.until.dba.BaseDomain;
import com.java.until.dba.PageModel;
import com.java.until.validate.Validate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * 菜单(sys_user)
 */
public class SysMenuDto extends BaseDomain {
    /**
     * 唯一标示
     */
    private String id;

    /**
     * 类型(1 系统菜单 2 子菜单 3 外部链接)
     */
    @Validate(message = "类型[type]不能为空！", required = true)
    private String type;

    /**
     * 菜单路径
     */
    //@Validate(message = "链接地址[url]不能为空！", required = true)
    private String url;

    /**
     * 名称
     */
    @Validate(message = "名称[name]不能为空！", required = true)
    private String name;
    //@Validate(message = "上级菜单ID[parentId]不能为空！", required = true)
    private String parentId;//上级菜单内码
    private String parentName;

    private String icon; //图标

    /**
     * 菜单等级
     */
    //@Validate(message = "菜单等级[menuLevel]不能为空！", required = true)
    private String menuLevel;

    /**
     * 排序号
     */
    @Validate(message = "排序号[orderNum]不能为空！", required = true)
    private String orderNum;
    @Validate(message = "状态[status]不能为空！", required = true)
    private String status; //状态(1正常2冻结3删除)
    private String roleId;
    private List childrenList;

    private String handleType;//操作类型(1添加2编辑3删除)

    private PageModel page;
    private Integer pageNo;
    private Integer pageSize;

    private List<String> idList;

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

    public PageModel getPage() {
        return page;
    }

    public void setPage(PageModel page) {
        this.page = page;
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
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

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List childrenList) {
        this.childrenList = childrenList;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
