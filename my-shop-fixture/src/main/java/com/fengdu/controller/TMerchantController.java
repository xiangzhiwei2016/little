package com.fengdu.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fengdu.entity.TMerchantEntity;
import com.fengdu.service.TMerchantService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 商户表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 17:00:44
 */
@Controller
@RequestMapping("tmerchant")
public class TMerchantController {

	@Autowired
	private TMerchantService tMerchantService;


	/**
	 * 查看列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tmerchant:list")
	@ResponseBody
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);

		List<TMerchantEntity> tMerchantList = tMerchantService.queryList(query);
		int total = tMerchantService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(tMerchantList, total,
				query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 查看信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tmerchant:info")
	@ResponseBody
	public R info(@PathVariable("id") Long id) {
		TMerchantEntity tMerchant = tMerchantService.queryObject(id);

		return R.ok().put("tMerchant", tMerchant);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tmerchant:save")
	@ResponseBody
	public R save(@RequestBody TMerchantEntity tMerchant) {
		tMerchantService.save(tMerchant);
		return R.ok();
	}

	/**
	 * 修改（密码修改）
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tmerchant:update")
	@ResponseBody
	public R update(@RequestBody TMerchantEntity tMerchant) {
		tMerchantService.update(tMerchant);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tmerchant:delete")
	@ResponseBody
	public R delete(@RequestBody Long[] ids) {
		tMerchantService.deleteBatch(ids);

		return R.ok();
	}

	/**
	 * 查看所有列表
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public R queryAll(@RequestParam Map<String, Object> params) {

		List<TMerchantEntity> list = tMerchantService.queryList(params);

		return R.ok().put("list", list);
	}
	
	/**
	 * 修改状态
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	public R updateStatus(@RequestBody String params) {
		JSONObject object = JSONObject.parseObject(params);
		Long id = Long.parseLong(object.get("id").toString());
		String status = object.get("status").toString();
		tMerchantService.updateStatus(id,status);
		return R.ok();
	}

}
