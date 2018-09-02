package com.fengdu.entity;

import java.io.Serializable;

/**
 * 用于首页统计
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 21:50:02
 */
public class MainCountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 昨日新增关注人数
	private int yesterdayForce = 0;

	// 总关注人数
	private int totalForce = 0;

	// 昨日浏览人数
	private int yesterdayBrowse = 0;

	// 总浏览人数
	private int totalBrowse = 0;

	// 昨日交定金人数
	private int yesterdayDeposit = 0;

	// 总交定金人数
	private int totalDeposit = 0;

	public int getYesterdayForce() {
		return yesterdayForce;
	}

	public void setYesterdayForce(int yesterdayForce) {
		this.yesterdayForce = yesterdayForce;
	}

	public int getTotalForce() {
		return totalForce;
	}

	public void setTotalForce(int totalForce) {
		this.totalForce = totalForce;
	}

	public int getYesterdayBrowse() {
		return yesterdayBrowse;
	}

	public void setYesterdayBrowse(int yesterdayBrowse) {
		this.yesterdayBrowse = yesterdayBrowse;
	}

	public int getTotalBrowse() {
		return totalBrowse;
	}

	public void setTotalBrowse(int totalBrowse) {
		this.totalBrowse = totalBrowse;
	}

	public int getYesterdayDeposit() {
		return yesterdayDeposit;
	}

	public void setYesterdayDeposit(int yesterdayDeposit) {
		this.yesterdayDeposit = yesterdayDeposit;
	}

	public int getTotalDeposit() {
		return totalDeposit;
	}

	public void setTotalDeposit(int totalDeposit) {
		this.totalDeposit = totalDeposit;
	}
}
