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

import com.fengdu.entity.TCaseEntity;
import com.fengdu.entity.TImageEntity;
import com.fengdu.service.TCaseDetailService;
import com.fengdu.service.TCaseService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 案例表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 15:44:39
 */
@Controller
@RequestMapping("tcase")
public class TCaseController {
    @Autowired
    private TCaseService tCaseService;
    
    @Autowired
    private TCaseDetailService tCaseDetailService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("tcase:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<TCaseEntity> tCaseList = tCaseService.queryList(query);
        int total = tCaseService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(tCaseList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("tcase:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        TCaseEntity tCase = tCaseService.queryObject(id);

        return R.ok().put("tCase", tCase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("tcase:save")
    @ResponseBody
    public R save(@RequestBody TCaseEntity tCase) {
        tCaseService.save(tCase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tcase:update")
    @ResponseBody
    public R update(@RequestBody TCaseEntity tCase) {
        tCaseService.update(tCase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("tcase:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        tCaseService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TCaseEntity> list = tCaseService.queryList(params);

        return R.ok().put("list", list);
    }
    
    /**
     * 查看所有图片
     */
    @RequestMapping("/queryImages")
    @ResponseBody
    public R queryImages(@RequestParam Map<String, Object> params) {
    	Object id = params.get("id");
        List<TImageEntity> list = tCaseService.queryImages(Long.parseLong(id.toString()));
        return R.ok().put("list", list);
    }
}
