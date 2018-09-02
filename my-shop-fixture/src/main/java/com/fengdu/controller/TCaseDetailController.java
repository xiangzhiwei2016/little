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

import com.fengdu.entity.TCaseDetailEntity;
import com.fengdu.entity.TCaseEntity;
import com.fengdu.entity.TImageEntity;
import com.fengdu.service.TCaseDetailService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 案例详情表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-07 11:28:31
 */
@Controller
@RequestMapping("tcasedetail")
public class TCaseDetailController {
    @Autowired
    private TCaseDetailService tCaseDetailService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("tcasedetail:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TCaseDetailEntity> tCaseDetailList = tCaseDetailService.queryList(query);
        int total = tCaseDetailService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(tCaseDetailList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("tcasedetail:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        TCaseDetailEntity tCaseDetail = tCaseDetailService.queryObject(id);

        return R.ok().put("tCaseDetail", tCaseDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("tcasedetail:save")
    @ResponseBody
    public R save(@RequestBody TCaseDetailEntity tCaseDetail) {
        tCaseDetailService.save(tCaseDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tcasedetail:update")
    @ResponseBody
    public R update(@RequestBody TCaseDetailEntity tCaseDetail) {
        tCaseDetailService.update(tCaseDetail);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("tcasedetail:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        tCaseDetailService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TCaseDetailEntity> list = tCaseDetailService.queryList(params);

        return R.ok().put("list", list);
    }
    
    /**
     * 查看所有图片
     */
    @RequestMapping("/queryImages")
    @ResponseBody
    public R queryImages(@RequestParam Map<String, Object> params) {
    	Object id = params.get("id");
        List<TImageEntity> list = tCaseDetailService.queryImages(Long.parseLong(id.toString()));
        return R.ok().put("list", list);
    }
    
    @RequestMapping("/queryAllCases")
    @ResponseBody
    public R queryAllCases(@RequestParam Map<String, Object> params) {
        List<TCaseEntity> list = tCaseDetailService.queryAllCases(params);
        return R.ok().put("list", list);
    }
    
}
