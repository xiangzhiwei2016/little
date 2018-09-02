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

import com.fengdu.entity.TOrdEntity;
import com.fengdu.service.TOrdService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-08-18 20:56:29
 */
@Controller
@RequestMapping("tord")
public class TOrdController {
    @Autowired
    private TOrdService tOrdService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("tord:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TOrdEntity> tOrdList = tOrdService.queryList(query);
        int total = tOrdService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(tOrdList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("tord:info")
    @ResponseBody
    public R info(@PathVariable("id") Integer id) {
        TOrdEntity tOrd = tOrdService.queryObject(id);

        return R.ok().put("tOrd", tOrd);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("tord:save")
    @ResponseBody
    public R save(@RequestBody TOrdEntity tOrd) {
        tOrdService.save(tOrd);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tord:update")
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
    public R delete(@RequestBody Integer[]ids) {
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

        return R.ok().put("list", list);
    }
}
