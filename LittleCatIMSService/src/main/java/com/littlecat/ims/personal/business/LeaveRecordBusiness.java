package com.littlecat.ims.personal.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.common.consts.LeaveRecordState;
import com.littlecat.ims.personal.dao.LeaveRecordDao;
import com.littlecat.ims.personal.model.LeaveRecordMO;

@Component
@Transactional
public class LeaveRecordBusiness
{
	@Autowired
	private LeaveRecordDao leaveRecordDao;

	public void approve(String id, boolean pass) throws LittleCatException
	{
		if (pass)
		{
			leaveRecordDao.setState(id, LeaveRecordState.审批通过);
		}
		else
		{
			leaveRecordDao.setState(id, LeaveRecordState.审批不通过);
		}
	}

	public void jiesuan(String id) throws LittleCatException
	{
		leaveRecordDao.setState(id, LeaveRecordState.已结算);
	}

	public void buban(String id) throws LittleCatException
	{
		leaveRecordDao.setState(id, LeaveRecordState.已补班);
	}

	public LeaveRecordMO getById(String id) throws LittleCatException
	{
		return leaveRecordDao.getById(id);
	}

	public void modify(LeaveRecordMO mo) throws LittleCatException
	{
		leaveRecordDao.modify(mo);
	}

	public String add(LeaveRecordMO mo) throws LittleCatException
	{
		return leaveRecordDao.add(mo);
	}
	public void delete(String id) throws LittleCatException
	{
		leaveRecordDao.delete(id);
	}

	public List<LeaveRecordMO> getList(String staff, String key, String state, String beginDate, String endDate) throws LittleCatException
	{
		return leaveRecordDao.getList(staff, key, state, beginDate, endDate);
	}

}
