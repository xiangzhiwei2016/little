package com.fengdu.service.impl;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fengdu.constants.Constants;
import com.fengdu.dao.TMerchantDao;
import com.fengdu.entity.SysRoleEntity;
import com.fengdu.entity.SysUserEntity;
import com.fengdu.entity.TMerchantEntity;
import com.fengdu.service.SysRoleService;
import com.fengdu.service.SysUserRoleService;
import com.fengdu.service.SysUserService;
import com.fengdu.service.TMerchantService;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.ShiroUtils;

/**
 * 商户表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 17:00:44
 */
@Service("tMerchantService")
public class TMerchantServiceImpl implements TMerchantService {
    @Autowired
    private TMerchantDao tMerchantDao;

    @Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private SysRoleService sysRoleService;
	
    @Override
    public TMerchantEntity queryObject(Long id) {
        return tMerchantDao.queryObject(id);
    }

    @Override
    public List<TMerchantEntity> queryList(Map<String, Object> map) {
        return tMerchantDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return tMerchantDao.queryTotal(map);
    }

    @Transactional
    @Override
    public int save(TMerchantEntity tMerchant) {
    	int merchantMaxId = tMerchantDao.queryMaxId();
    	int sysUserMaxId = sysUserService.queryMaxId();
    	// 获取他们中最大的id+1
    	long lastMaxId = Long.parseLong(String.valueOf(compareMaxId(merchantMaxId,sysUserMaxId)));
    	String password = tMerchant.getPassWord();
    	tMerchant.setCreateTime(DateUtils.currentDate());
    	tMerchant.setCreator(ShiroUtils.getUserName());
    	tMerchant.setStatus(String.valueOf(Constants.ENABLED));
		// sha256加密
		tMerchant.setPassWord(new Sha256Hash(password).toHex());
		tMerchant.setId(lastMaxId);
		int count = tMerchantDao.save(tMerchant);
		// 同步更新到sys_user
		SysUserEntity user = new SysUserEntity();
		user.setUserId(lastMaxId);
		user.setFullName(tMerchant.getMerchantName());
		user.setUsername(tMerchant.getMcLoginName());
		user.setPassword(password);
		// 同步更新到sys_user_role
		List<Long> roleIdList = new ArrayList<Long>();
		// 默认角色是merchant
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(Constants.ROLE_NAME, Constants.DEFAULT_ROLE);
		List<SysRoleEntity> roleList = sysRoleService.queryList(param);
		for (SysRoleEntity role : roleList) {
			roleIdList.add(role.getRoleId());
		}
		user.setRoleIdList(roleIdList);
		// 状态至为可用
		user.setStatus(Constants.ENABLED);
		sysUserService.save(user);
    	
        return count;
    }
    
	/**
	 * 返回大的id+1
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	private int compareMaxId(int id1, int id2) {
		if (id1 > id2) {
			return id1+1;
		}
		return id2+1;
	}
	
    @Override
    @Transactional
    public int update(TMerchantEntity tMerchant) {
    	Boolean flag = Boolean.FALSE;
		String password = tMerchant.getPassWord();
		Long merchantId = tMerchant.getId();
		TMerchantEntity oldEntity =queryObject(merchantId);
		if (!oldEntity.getMcLoginName().equals(tMerchant.getMcLoginName())) {
			// 商品名称也修改了
			flag = Boolean.TRUE;
		}
		// sha256加密
		tMerchant.setPassWord(new Sha256Hash(password).toHex());
    	tMerchant.setModifyTime(DateUtils.currentDate());
    	tMerchant.setModifier(ShiroUtils.getUserName());
		int count = tMerchantDao.update(tMerchant);

		// 同步更新到sys_user
		String oldUserName = oldEntity.getMcLoginName();
		SysUserEntity user = sysUserService.queryByUserName(oldUserName);
		// 若商户名称也修改
		if (flag) {
			user.setUsername(tMerchant.getMcLoginName());
		}
		user.setFullName(tMerchant.getMerchantName());
		user.setPassword(password);
		sysUserService.updateUser(user);
        return count;
    }

    @Override
    public int delete(Long id) {
        return tMerchantDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return tMerchantDao.deleteBatch(ids);
    }
    
    @Override
    public int updateStatus(Long id,String status) {
    	TMerchantEntity merchant = queryObject(id);
    	merchant.setStatus(status);
		// 同步更新到sys_user
		String userName = merchant.getMcLoginName();
		SysUserEntity user = sysUserService.queryByUserName(userName);
		// 改变状态
		user.setStatus(Integer.parseInt(status));
		// 默认角色是merchant
		sysUserService.updateUser(user);
		
        return tMerchantDao.update(merchant);
    }
}
