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

import com.fengdu.entity.TImageEntity;
import com.fengdu.service.TImageService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 图片表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 22:33:00
 */
@Controller
@RequestMapping("image/upload")
public class TImageController {
    @Autowired
    private TImageService tImageService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("timage:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TImageEntity> tImageList = tImageService.queryList(query);
        int total = tImageService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(tImageList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("timage:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        TImageEntity tImage = tImageService.queryObject(id);

        return R.ok().put("tImage", tImage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("timage:save")
    @ResponseBody
    public R save(@RequestBody TImageEntity tImage) {
        tImageService.save(tImage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("timage:update")
    @ResponseBody
    public R update(@RequestBody TImageEntity tImage) {
        tImageService.update(tImage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("timage:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        tImageService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TImageEntity> list = tImageService.queryList(params);

        return R.ok().put("list", list);
    }
}
