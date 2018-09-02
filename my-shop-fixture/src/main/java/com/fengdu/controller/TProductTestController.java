package com.fengdu.controller;

import java.util.Date;
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

import com.fengdu.entity.TProductTestEntity;
import com.fengdu.service.TProductTestService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-24 11:03:19
 */
@Controller
@RequestMapping("tproducttest")
public class TProductTestController {
	@Autowired
	private TProductTestService tProductTestService;

	/**
	 * 查看列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tproducttest:list")
	@ResponseBody
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);

		List<TProductTestEntity> tProductTestList = tProductTestService
				.queryList(query);
		int total = tProductTestService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(tProductTestList, total,
				query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 查看信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tproducttest:info")
	@ResponseBody
	public R info(@PathVariable("id") Long id) {
		TProductTestEntity tProductTest = tProductTestService.queryObject(id);

		return R.ok().put("tProductTest", tProductTest);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tproducttest:save")
	@ResponseBody
	public R save(@RequestBody TProductTestEntity tProductTest) {
		tProductTest.setCreateTime(new Date());
		tProductTestService.save(tProductTest);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tproducttest:update")
	@ResponseBody
	public R update(@RequestBody TProductTestEntity tProductTest) {
		tProductTest.setModifyTime(new Date());
		tProductTestService.update(tProductTest);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tproducttest:delete")
	@ResponseBody
	public R delete(@RequestBody Long[] ids) {
		tProductTestService.deleteBatch(ids);

		return R.ok();
	}

	/**
	 * 查看所有列表
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public R queryAll(@RequestParam Map<String, Object> params) {

		List<TProductTestEntity> list = tProductTestService.queryList(params);

		return R.ok().put("list", list);
	}
}
