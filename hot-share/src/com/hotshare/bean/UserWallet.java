package com.hotshare.bean;

import java.util.Date;


/**
 * 前端用户钱包Bean
 * 
 * @author gkx
 * @version 1.0.0
 * @2016-8-1 上午9:26:37
 */
public class UserWallet {

	private int userWalletId;  //用户钱包id

	private String alipayAccount;//  支付宝账号

	private String alipayName;//   支付宝名称

	private short state;//状态     0正常,-1禁用
	
	private Date updateTime;//更新时间
	
	private double balance;  //余额
	
	private double commission;  //佣金
	
	private double recharge;  //剩余充值余额
	
	private double income;  //收入
	
	private User user;  //所属用户

	
	
	public int getUserWalletId() {
		return userWalletId;
	}

	public void setUserWalletId(int userWalletId) {
		this.userWalletId = userWalletId;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public String getAlipayName() {
		return alipayName;
	}

	public void setAlipayName(String alipayName) {
		this.alipayName = alipayName;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double getRecharge() {
		return recharge;
	}

	public void setRecharge(double recharge) {
		this.recharge = recharge;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
