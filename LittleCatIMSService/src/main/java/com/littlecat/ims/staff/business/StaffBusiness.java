package com.littlecat.ims.staff.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.staff.dao.StaffDao;
import com.littlecat.ims.staff.model.StaffMO;

@Component
@Transactional
public class StaffBusiness
{
	@Autowired
	private StaffDao staffDao;

	public StaffMO getById(String id) throws LittleCatException
	{
		return staffDao.getById(id);
	}

	public void modify(StaffMO mo) throws LittleCatException
	{
		staffDao.modify(mo);
	}

	public String add(StaffMO mo) throws LittleCatException
	{
		return staffDao.add(mo);
	}

	public List<StaffMO> getList(String key, String state) throws LittleCatException
	{
		return staffDao.getList(key, state);
	}
}
