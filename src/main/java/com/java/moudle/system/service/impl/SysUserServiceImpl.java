package com.java.moudle.system.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.java.moudle.common.message.JsonResult;
import com.java.moudle.system.dao.SysUserRoleDao;
import com.java.moudle.system.domain.SysUserRole;
import com.java.moudle.system.dto.SysUserDto;
import com.java.until.StringUtils;
import com.java.until.UUIDUtil;
import com.java.until.ras.BCrypt;
import org.springframework.transaction.annotation.Transactional;

import com.java.moudle.common.service.impl.BaseServiceImpl;
import com.java.moudle.system.dao.SysRoleDao;
import com.java.moudle.system.dao.SysUserDao;
import com.java.moudle.system.domain.SysRole;
import com.java.moudle.system.domain.SysUser;
import com.java.moudle.system.service.SysUserService;

import java.util.Date;


@Named
@Transactional(readOnly = false)
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Inject
    private SysRoleDao sysRoleDao;
    @Inject
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public SysUser getUserInfoByName(String username) {
        return dao.getUserInfoByName(username);
    }

    @Override
    public SysUser queryInfoByCon(String id, String username) {
        SysUser sysUser = dao.queryInfoByCon("", username);
        //sysUser.setPassword(null);
        //权限中心判断Authorities不能为空
        // 根据用户id查询角色信息
        if (sysUser != null) {
            SysRole sysRole = sysRoleDao.getRoleInfoByUserId(sysUser.getId());
            if (sysRole != null) {
                sysUser.setRoleId(sysRole.getId());
            }
        }
        return sysUser;
    }

    //查询用户列表（分页）
    @Override
    public void getUserPage(SysUserDto request) {
        dao.getUserPage(request);
    }

    //保存用户
    @Override
    public JsonResult saveUser(SysUser user) {
        SysUser query = dao.getUserInfoByName(user.getUsername());
        //密码加密
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        sysUserRoleDao.delRoleByUserId(user.getId());
        if (StringUtils.isBlank(user.getId())) {
            //新增用户
            if (query != null)
                return new JsonResult(null, 9001, "用户名重复，请重新填写。");
            user.setId(UUIDUtil.getUUID());
            user.setCreaterTime(new Date());
            user.setStatus("1");
            user.setPassword(password);
            dao.save(user);
        } else {
            //修改用户
            if (query != null && !user.getId().equals(query.getId()) && user.getUsername().equals(query.getUsername()))
                return new JsonResult(null, 9001, "用户名重复，请重新填写。");
            SysUser sysUser = dao.get(user.getId());
            sysUser.setUsername(user.getUsername());
            if (StringUtils.isNotBlank(user.getPassword()))
                sysUser.setPassword(password);
            sysUser.setOrgId(user.getOrgId());
            sysUser.setOrgName(user.getOrgName());
            sysUser.setName(user.getName());
            sysUser.setRemark(sysUser.getRemark());
            dao.save(sysUser);
        }
        SysUserRole userRole = new SysUserRole();
        userRole.setId(UUIDUtil.getUUID());
        userRole.setUserId(user.getId());
        userRole.setRoleId(user.getRoleId());
        sysUserRoleDao.save(userRole);
        return new JsonResult();
    }

    //查询用户详情
    @Override
    public SysUserDto getUser(String id) {
        return dao.getUserInfo(id);
    }

    //修改状态
    @Override
    public void updateStatus(SysUserDto request) {
        SysUser user = dao.get(request.getId());
        if (user != null && (("1").equals(request.getStatus()) || ("2").equals(request.getStatus()))) {
            user.setStatus(request.getStatus());
            dao.save(user);
        }
    }


    //重置密码
    @Override
    public void reset(String id) {
        SysUser user = dao.get(id);
        if (user != null) {
            String password = BCrypt.hashpw("123456", BCrypt.gensalt());
            user.setPassword(password);
            dao.save(user);
        }
    }

    //修改密码
    @Override
    public JsonResult changePwd(SysUserDto user) {
        SysUser query = dao.get(user.getId());
        boolean is = BCrypt.checkpw(user.getOldPwd(), query.getPassword());
        if (!is)
            return new JsonResult(null, 9001, "密码错误！");
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        query.setPassword(password);
        dao.save(query);
        return new JsonResult();
    }


}
