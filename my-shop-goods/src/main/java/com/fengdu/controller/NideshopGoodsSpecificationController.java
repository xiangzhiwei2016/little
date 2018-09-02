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
import org.springframework.web.bind.annotation.RestController;

import com.fengdu.entity.NideshopGoodsSpecificationEntity;
import com.fengdu.service.NideshopGoodsSpecificationService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 商品对应规格表值表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-24 10:30:31
 */
@Controller
@RequestMapping("nideshopgoodsspecification")
public class NideshopGoodsSpecificationController {
    @Autowired
    private NideshopGoodsSpecificationService nideshopGoodsSpecificationService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("nideshopgoodsspecification:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<NideshopGoodsSpecificationEntity> nideshopGoodsSpecificationList = nideshopGoodsSpecificationService.queryList(query);
        int total = nideshopGoodsSpecificationService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(nideshopGoodsSpecificationList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("nideshopgoodsspecification:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        NideshopGoodsSpecificationEntity nideshopGoodsSpecification = nideshopGoodsSpecificationService.queryObject(id);

        return R.ok().put("nideshopGoodsSpecification", nideshopGoodsSpecification);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("nideshopgoodsspecification:save")
    @ResponseBody
    public R save(@RequestBody NideshopGoodsSpecificationEntity nideshopGoodsSpecification) {
        nideshopGoodsSpecificationService.save(nideshopGoodsSpecification);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("nideshopgoodsspecification:update")
    @ResponseBody
    public R update(@RequestBody NideshopGoodsSpecificationEntity nideshopGoodsSpecification) {
        nideshopGoodsSpecificationService.update(nideshopGoodsSpecification);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("nideshopgoodsspecification:delete")
    @ResponseBody
    public R delete(@RequestBody Integer[]ids) {
        nideshopGoodsSpecificationService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<NideshopGoodsSpecificationEntity> list = nideshopGoodsSpecificationService.queryList(params);

        return R.ok().put("list", list);
    }
}
