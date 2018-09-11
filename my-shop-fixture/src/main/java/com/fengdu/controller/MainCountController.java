package com.fengdu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fengdu.entity.MainCountEntity;
import com.fengdu.service.TClientService;
import com.fengdu.service.TOrdService;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.R;

/**
 * 门店表Controller
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 21:50:02
 */
@Controller
@RequestMapping("maincount")
public class MainCountController {
    @Autowired
    private TClientService tClientService;

    @Autowired
    private TOrdService tOrdService;
   
    /**
     * 统计信息
     */
    @RequestMapping("/count")
    @ResponseBody
    public R count() {
    	Map<String, Object> map = new HashMap<String, Object>();
    	MainCountEntity countEntity = new MainCountEntity(); 
    	// 获取昨日日期
    	String yesterDay = DateUtils.format(DateUtils.getNDate(-1),DateUtils.DATE_PATTERN);
    	// 总浏览人数
    	int totalBrowse = tClientService.queryTotal(map);
    	countEntity.setTotalBrowse(totalBrowse);
    	// 总关注人数
    	int totalForce = tClientService.queryTotal(map);
    	countEntity.setTotalForce(totalForce);
    	// 总缴纳定金人数
    	int totalDeposit = tOrdService.queryTotal(map);
    	countEntity.setTotalDeposit(totalDeposit);
    	// 昨日新增关注人数
    	map.put("registerTime", yesterDay);
    	int yesterdayForce = tClientService.queryTotal(map);
    	countEntity.setYesterdayForce(yesterdayForce);
    	// 昨日浏览人数
    	map.put("registerTime", null);
    	map.put("lastLoginTime", yesterDay);
    	int yesterdayBrowse = tClientService.queryTotal(map);
    	countEntity.setYesterdayBrowse(yesterdayBrowse);
    	// 昨日缴纳定金人数
    	map.put("addTime", yesterDay);
    	int yesterdayDeposit = tOrdService.queryTotal(map);
    	countEntity.setYesterdayDeposit(yesterdayDeposit);
    	
        return R.ok().put("countResult", countEntity);
    }
}
