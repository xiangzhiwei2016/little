package com.fengdu.controller;

import java.util.ArrayList;
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

import com.fengdu.constants.OrderStatusEnum;
import com.fengdu.entity.TOrdEntity;
import com.fengdu.service.TOrdService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 订单表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 14:08:57
 */
@Controller
@RequestMapping("torder")
public class TOrderController {
	@Autowired
	private TOrdService tOrdService;

	/**
	 * 修改状态
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	public R updateStatus(@RequestBody Integer id) {
		tOrdService.updateStatus(id);
		return R.ok();
	}

	/**
	 * 查看列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("torder:list")
	@ResponseBody
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<TOrdEntity> resultList  = new ArrayList<TOrdEntity>();
		List<TOrdEntity> tOrdList = tOrdService.queryList(query);
		for (TOrdEntity entity : tOrdList) {
			int status = entity.getOrderStatus();
			if(OrderStatusEnum.ORDER_NOT_PAYED.getCode() == status || OrderStatusEnum.ORDER_BACK.getCode() == status){
				// 未付款的,已退货的过滤掉
				continue;
			}else if(OrderStatusEnum.ORDER_WATING.getCode() == status 
					|| OrderStatusEnum.ORDER_RECIVEDD.getCode() == status
							|| OrderStatusEnum.ORDER_FINISH.getCode() == status){
				// 其他置为已付款
				status = OrderStatusEnum.ORDER_PAYED.getCode();
				
				// 1：支付定金 ，2：购买商品
				if("1".equals(entity.getOrderType())){
					entity.setDetailDesc(String.format("支付定金%s元", entity.getTotalFee()));
				}else if("2".equals(entity.getOrderType())){
					entity.setDetailDesc(String.format("购买%s", entity.getProductName()==null ?"":entity.getProductName()));
				}
			}
			entity.setOrderStatusDesc(OrderStatusEnum.getTextByCode(status));
			resultList.add(entity);
		}
//		int total = tOrdService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(resultList, resultList.size(), query.getLimit(),
				query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 查看信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("torder:info")
	@ResponseBody
	public R info(@PathVariable("id") Integer id) {
		TOrdEntity tOrd = tOrdService.queryObject(id);
		int status = tOrd.getOrderStatus();
		tOrd.setOrderStatusDesc(OrderStatusEnum.getTextByCode(status));
		return R.ok().put("tOrder", tOrd);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("torder:save")
	@ResponseBody
	public R save(@RequestBody TOrdEntity tOrd) {
		tOrdService.save(tOrd);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("torder:update")
	@ResponseBody
	public R update(@RequestBody TOrdEntity tOrd) {
		tOrdService.update(tOrd);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tord:delete")
	@ResponseBody
	public R delete(@RequestBody Integer[] ids) {
		tOrdService.deleteBatch(ids);

		return R.ok();
	}

	/**
	 * 查看所有列表
	 */
	@RequestMapping("/queryAll")
	@ResponseBody
	public R queryAll(@RequestParam Map<String, Object> params) {

		List<TOrdEntity> list = tOrdService.queryList(params);
		for (TOrdEntity entity : list) {
			int status = entity.getOrderStatus();
			entity.setOrderStatusDesc(OrderStatusEnum.getTextByCode(status));
		}
		return R.ok().put("list", list);
	}

}
