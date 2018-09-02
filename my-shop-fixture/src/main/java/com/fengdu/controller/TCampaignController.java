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

import com.fengdu.entity.TCampaignEntity;
import com.fengdu.service.TCampaignService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 营销活动表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-01 15:11:47
 */
@Controller
@RequestMapping("tcampaign")
public class TCampaignController {
    @Autowired
    private TCampaignService tCampaignService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("tcampaign:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TCampaignEntity> tCampaignList = tCampaignService.queryList(query);
        int total = tCampaignService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(tCampaignList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("tcampaign:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        TCampaignEntity tCampaign = tCampaignService.queryObject(id);

        return R.ok().put("tCampaign", tCampaign);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("tcampaign:save")
    @ResponseBody
    public R save(@RequestBody TCampaignEntity tCampaign) {
        tCampaignService.save(tCampaign);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tcampaign:update")
    @ResponseBody
    public R update(@RequestBody TCampaignEntity tCampaign) {
        tCampaignService.update(tCampaign);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("tcampaign:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        tCampaignService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TCampaignEntity> list = tCampaignService.queryList(params);

        return R.ok().put("list", list);
    }
}
