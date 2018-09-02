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

import com.fengdu.entity.TCategoryEntity;
import com.fengdu.service.TCategoryService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 类别表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 16:03:53
 */
@Controller
@RequestMapping("tcategory")
public class TCategoryController {
    @Autowired
    private TCategoryService tCategoryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("tcategory:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TCategoryEntity> tCategoryList = tCategoryService.queryList(query);
        int total = tCategoryService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(tCategoryList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("tcategory:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        TCategoryEntity tCategory = tCategoryService.queryObject(id);

        return R.ok().put("tCategory", tCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("tcategory:save")
    @ResponseBody
    public R save(@RequestBody TCategoryEntity tCategory) {
        tCategoryService.save(tCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tcategory:update")
    @ResponseBody
    public R update(@RequestBody TCategoryEntity tCategory) {
        tCategoryService.update(tCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("tcategory:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        tCategoryService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TCategoryEntity> list = tCategoryService.queryList(params);

        return R.ok().put("list", list);
    }
}
