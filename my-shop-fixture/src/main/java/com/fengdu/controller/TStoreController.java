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

import com.fengdu.entity.TImageEntity;
import com.fengdu.entity.TStoreEntity;
import com.fengdu.service.TStoreService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 门店表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 21:50:02
 */
@Controller
@RequestMapping("tstore")
public class TStoreController {
    @Autowired
    private TStoreService tStoreService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("tstore:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TStoreEntity> tStoreList = tStoreService.queryList(query);
        int total = tStoreService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(tStoreList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("tstore:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        TStoreEntity tStore = tStoreService.queryObject(id);

        return R.ok().put("tStore", tStore);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("tstore:save")
    @ResponseBody
    public R save(@RequestBody TStoreEntity tStore) {
        List<TStoreEntity> list = tStoreService.queryList(new HashMap<String,Object>());
        if(null != list && !list.isEmpty()){
        	return R.error("已存在门店信息，不能再次添加！");
        }
        tStoreService.save(tStore);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tstore:update")
    @ResponseBody
    public R update(@RequestBody TStoreEntity tStore) {
        tStoreService.update(tStore);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("tstore:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        tStoreService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TStoreEntity> list = tStoreService.queryList(params);

        return R.ok().put("list", list);
    }
    
    /**
     * 查看所有图片
     */
    @RequestMapping("/queryImages")
    @ResponseBody
    public R queryImages(@RequestParam Map<String, Object> params) {
    	Object id = params.get("id");
        List<TImageEntity> list = tStoreService.queryImages(Long.parseLong(id.toString()));
        return R.ok().put("list", list);
    }
    
}
