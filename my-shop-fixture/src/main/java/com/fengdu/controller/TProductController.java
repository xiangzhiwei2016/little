package com.fengdu.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengdu.entity.TImageEntity;
import com.fengdu.entity.TProductEntity;
import com.fengdu.service.TProductService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 商品表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 14:55:47
 */
@Controller
@RequestMapping("tproduct")
public class TProductController {
	@Autowired
	private TProductService tProductService;

	/**
	 * 查看列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tproduct:list")
	@ResponseBody
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);

		List<TProductEntity> tProductList = tProductService.queryList(query);
		for (TProductEntity entity : tProductList) {
			entity.setPriceStr(entity.getPrice() + entity.getUnit());
			entity.setVipPriceStr(entity.getVipPrice() + entity.getUnit());
			String tag = entity.getTag();
			entity.setTagDesc(tag);
			if (tag.indexOf(",") >= 0) {
				String[] tags = tag.split(",");
				entity.setTag(tags[0]);
				entity.setTag2(tags[1]);
			}
		}
		int total = tProductService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(tProductList, total,
				query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 查看信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tproduct:info")
	@ResponseBody
	public R info(@PathVariable("id") Long id) {
		TProductEntity tProduct = tProductService.queryObject(id);
		tProduct.setPriceStr(tProduct.getPrice() + tProduct.getUnit());
		tProduct.setVipPriceStr(tProduct.getVipPrice() + tProduct.getUnit());
		String tag = tProduct.getTag();
		tProduct.setTagDesc(tag);
		if (tag.indexOf(",") >= 0) {
			String[] tags = tag.split(",");
			tProduct.setTag(tags[0]);
			tProduct.setTag2(tags[1]);
		}
		return R.ok().put("tProduct", tProduct);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tproduct:save")
	@ResponseBody
	public R save(@RequestBody TProductEntity tProduct) {
		String tag1 = tProduct.getTag();
		String tag2 = tProduct.getTag2();
		String newTag = tag1;
		if (!StringUtils.isBlank(tag1) && !StringUtils.isBlank(tag2)) {
			newTag = tag1.concat(",").concat(tag2);
		} else if (StringUtils.isBlank(tag1) && !StringUtils.isBlank(tag2)) {
			newTag = tag2;
		}
		tProduct.setTag(newTag);
		tProductService.save(tProduct);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tproduct:update")
	@ResponseBody
	public R update(@RequestBody TProductEntity tProduct) {
		String tag1 = tProduct.getTag();
		String tag2 = tProduct.getTag2();
		String newTag = tag1;
		if (!StringUtils.isBlank(tag1) && !StringUtils.isBlank(tag2)) {
			newTag = tag1.concat(",").concat(tag2);
		} else if (StringUtils.isBlank(tag1) && !StringUtils.isBlank(tag2)) {
			newTag = tag2;
		}
		tProduct.setTag(newTag);
		tProductService.update(tProduct);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tproduct:delete")
	@ResponseBody
	public R delete(@RequestBody Long[] ids) {
		tProductService.deleteBatch(ids);

		return R.ok();
	}

	/**
	 * 查看所有列表
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public R queryAll(@RequestParam Map<String, Object> params) {

		List<TProductEntity> list = tProductService.queryList(params);
		for (TProductEntity entity : list) {
			entity.setPriceStr(entity.getPrice() + entity.getUnit());
			entity.setVipPriceStr(entity.getVipPrice() + entity.getUnit());
		}
		return R.ok().put("list", list);
	}

	/**
	 * 查看所有图片
	 */
	@RequestMapping("/queryImages")
	@ResponseBody
	public R queryImages(@RequestParam Map<String, Object> params) {
		Object id = params.get("id");
		List<TImageEntity> list = tProductService.queryImages(Long.parseLong(id
				.toString()));
		return R.ok().put("list", list);
	}

}
