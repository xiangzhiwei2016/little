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

import com.fengdu.entity.TClientEntity;
import com.fengdu.service.TClientService;
import com.fengdu.utils.PageUtils;
import com.fengdu.utils.Query;
import com.fengdu.utils.R;

/**
 * 客户表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-01 19:18:30
 */
@Controller
@RequestMapping("tclient")
public class TClientController {
    @Autowired
    private TClientService tClientService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("tclient:list")
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<TClientEntity> tClientList = tClientService.queryList(query);
        int total = tClientService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(tClientList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("tclient:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        TClientEntity tClient = tClientService.queryObject(id);

        return R.ok().put("tClient", tClient);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("tclient:save")
    @ResponseBody
    public R save(@RequestBody TClientEntity tClient) {
        tClientService.save(tClient);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("tclient:update")
    @ResponseBody
    public R update(@RequestBody TClientEntity tClient) {
        tClientService.update(tClient);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("tclient:delete")
    @ResponseBody
    public R delete(@RequestBody Long[]ids) {
        tClientService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public R queryAll(@RequestParam Map<String, Object> params) {

        List<TClientEntity> list = tClientService.queryList(params);

        return R.ok().put("list", list);
    }
    
    /**
	 * 修改状态
	 */
	@RequestMapping("/toSpread")
	@ResponseBody
	public R toSpread(@RequestBody Long id) {
		tClientService.toSpread(id);
		return R.ok();
	}
    
	  /**
     * 根据clientId查询所有推广进来的人
     * 推广总人数
     * @param goodsId
     * @return
     */
    @RequestMapping("/queryByClientId/{clientId}")
    @ResponseBody
    public R queryByClientId(@PathVariable("clientId") String clientId) {
        Map<String, Object> params = new HashMap<>();
        TClientEntity tClient = tClientService.queryObject(Long.parseLong(clientId));
        params.put("upClientId", clientId);
        // 总推广人数
        int total = tClientService.queryTotal(params);
        // 推广的会员数(用户类型（0.推广员，1.会员，2潜在用户）)
        params.put("clientType", "1");
        List<TClientEntity> list = tClientService.queryList(params);
        tClient.setTotalCount(String.valueOf(total));
        tClient.setMemberCount(String.valueOf(list.size()));
        return R.ok().put("tClient", tClient);
    }
}
