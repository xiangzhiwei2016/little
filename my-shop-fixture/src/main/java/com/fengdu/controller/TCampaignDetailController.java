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

import com.fengdu.entity.TCampaignDetailEntity;
import com.fengdu.service.TCampaignDetailService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 营销活动详情表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 16:40:09
 */
@Controller
@RequestMapping("tcampaigndetail")
public class TCampaignDetailController {
    @Autowired
    private TCampaignDetailService tCampaignDetailService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("tcampaigndetail:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TCampaignDetailEntity> tCampaignDetailList = tCampaignDetailService.queryList(query);
        int total = tCampaignDetailService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(tCampaignDetailList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("tcampaigndetail:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        TCampaignDetailEntity tCampaignDetail = tCampaignDetailService.queryObject(id);

        return R.ok().put("tCampaignDetail", tCampaignDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("tcampaigndetail:save")
    @ResponseBody
    public R save(@RequestBody TCampaignDetailEntity tCampaignDetail) {
        tCampaignDetailService.save(tCampaignDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tcampaigndetail:update")
    @ResponseBody
    public R update(@RequestBody TCampaignDetailEntity tCampaignDetail) {
        tCampaignDetailService.update(tCampaignDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("tcampaigndetail:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        tCampaignDetailService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TCampaignDetailEntity> list = tCampaignDetailService.queryList(params);

        return R.ok().put("list", list);
    }
}
