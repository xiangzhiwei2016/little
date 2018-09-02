package com.fengdu.controller;

import java.util.HashMap;
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

import com.fengdu.entity.TCampaignTypeEntity;
import com.fengdu.service.TCampaignDetailService;
import com.fengdu.service.TCampaignTypeService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 营销活动的类型Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-01 15:11:47
 */
@Controller
@RequestMapping("tcampaigntype")
public class TCampaignTypeController {
    @Autowired
    private TCampaignTypeService tCampaignTypeService;
    
    @Autowired
    private TCampaignDetailService tCampaignDetailService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("tcampaigntype:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TCampaignTypeEntity> tCampaignTypeList = tCampaignTypeService.queryList(query);
        Map<String, Object> paraCount = null;
        // 查找参与活动的人数
        for(TCampaignTypeEntity entity : tCampaignTypeList){
        	paraCount = new HashMap<String, Object>();
        	paraCount.put("typeId", entity.getId());
        	int count = tCampaignDetailService.queryTotal(paraCount);
        	entity.setCount(String.valueOf(count));
        }
        int total = tCampaignTypeService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(tCampaignTypeList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("tcampaigntype:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        TCampaignTypeEntity tCampaignType = tCampaignTypeService.queryObject(id);

        return R.ok().put("tCampaignType", tCampaignType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("tcampaigntype:save")
    @ResponseBody
    public R save(@RequestBody TCampaignTypeEntity tCampaignType) {
        String type = tCampaignType.getType();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        // 可用的
        map.put("status", "1");
        List<TCampaignTypeEntity> list = tCampaignTypeService.queryList(map);
        if(null != list && !list.isEmpty()){
        	return R.error("该营销活动已存在，不可以重复创建！请先撤销该活动！");
        }
        tCampaignTypeService.save(tCampaignType);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tcampaigntype:update")
    @ResponseBody
    public R update(@RequestBody TCampaignTypeEntity tCampaignType) {
        tCampaignTypeService.update(tCampaignType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("tcampaigntype:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        tCampaignTypeService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TCampaignTypeEntity> list = tCampaignTypeService.queryList(params);

        return R.ok().put("list", list);
    }
    
    /**
	 * 修改状态
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	public R updateStatus(@RequestBody Long id) {
		tCampaignTypeService.updateStatus(id);
		return R.ok();
	}
}
