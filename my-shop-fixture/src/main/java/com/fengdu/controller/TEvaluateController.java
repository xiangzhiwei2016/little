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

import com.fengdu.entity.TEvaluateEntity;
import com.fengdu.entity.TImageEntity;
import com.fengdu.service.TEvaluateService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 评价表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 16:52:11
 */
@Controller
@RequestMapping("tevaluate")
public class TEvaluateController {
    @Autowired
    private TEvaluateService tEvaluateService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("tevaluate:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TEvaluateEntity> tEvaluateList = tEvaluateService.queryList(query);
        int total = tEvaluateService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(tEvaluateList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("tevaluate:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        TEvaluateEntity tEvaluate = tEvaluateService.queryObject(id);

        return R.ok().put("tEvaluate", tEvaluate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("tevaluate:save")
    @ResponseBody
    public R save(@RequestBody TEvaluateEntity tEvaluate) {
        tEvaluateService.save(tEvaluate);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tevaluate:update")
    @ResponseBody
    public R update(@RequestBody TEvaluateEntity tEvaluate) {
        tEvaluateService.update(tEvaluate);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("tevaluate:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        tEvaluateService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TEvaluateEntity> list = tEvaluateService.queryList(params);

        return R.ok().put("list", list);
    }
    
    
    /**
     * 查看所有图片
     */
    @RequestMapping("/queryImages")
    @ResponseBody
    public R queryImages(@RequestParam Map<String, Object> params) {
    	Object id = params.get("id");
        List<TImageEntity> list = tEvaluateService.queryImages(Long.parseLong(id.toString()));
        return R.ok().put("list", list);
    }
    
}
