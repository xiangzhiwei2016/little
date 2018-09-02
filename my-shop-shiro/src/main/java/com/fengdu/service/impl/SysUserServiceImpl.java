package com.fengdu.service.impl;

import com.fengdu.Global;
import com.fengdu.dao.SysUserDao;
import com.fengdu.entity.SysUserEntity;
import com.fengdu.entity.UserWindowDto;
import com.fengdu.page.Page;
import com.fengdu.page.PageHelper;
import com.fengdu.service.SysRoleService;
import com.fengdu.service.SysUserRoleService;
import com.fengdu.service.SysUserService;
import com.fengdu.utils.Constant;
import com.fengdu.utils.RRException;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author 李鹏军
 * @email 2366207000@qq.com
 * @date 2016年12月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public List<String> queryAllPerms(Long userId) {
        return sysUserDao.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserDao.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }

    @Override
    public SysUserEntity queryObject(Long userId) {
        return sysUserDao.queryObject(userId);
    }

    @Override
    public List<SysUserEntity> queryList(Map<String, Object> map) {
        return sysUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysUserDao.queryTotal(map);
    }

    @Override
    @Transactional
    public void save(SysUserEntity user) {
        user.setCreateTime(new Date());
        //sha256加密
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(new Sha256Hash(Global.DEFAULT_PASS_WORD).toHex());
        } else {
            user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        }
        sysUserDao.save(user);

        //检查角色是否越权
//        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(SysUserEntity user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(new Sha256Hash(Global.DEFAULT_PASS_WORD).toHex());
        } else {
            user.setPassword(new Sha256Hash(user.getPassword()).toHex());
        }
        sysUserDao.update(user);

        //检查角色是否越权
//        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(Long[] userId) {
        sysUserDao.deleteBatch(userId);
    }

    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserDao.updatePassword(map);
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUserEntity user) {
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户创建的角色列表
        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        //判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new RRException("新增用户所选角色，不是本人创建");
        }
    }


    @Override
    public Page<UserWindowDto> findPage(UserWindowDto userWindowDto, int pageNum) {
        PageHelper.startPage(pageNum, Constant.pageSize);
        sysUserDao.queryListByBean(userWindowDto);
        return PageHelper.endPage();
    }
    
    @Override
    public int queryMaxId() {
        return sysUserDao.queryMaxId();
    }
    
    @Override
    public void updateUser(SysUserEntity user) {
        sysUserDao.update(user);
    }

}
