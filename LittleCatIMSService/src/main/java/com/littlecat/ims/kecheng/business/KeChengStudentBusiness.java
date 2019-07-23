package com.littlecat.ims.kecheng.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.kecheng.dao.KeChengStudentDao;
import com.littlecat.ims.kecheng.model.KeChengStudentMO;

@Component
@Transactional
public class KeChengStudentBusiness
{
	@Autowired
	private KeChengStudentDao keChengStudentDao;

	public void modify(KeChengStudentMO mo) throws LittleCatException
	{
		keChengStudentDao.modify(mo);
	}

	public String add(KeChengStudentMO mo) throws LittleCatException
	{
		return keChengStudentDao.add(mo);
	}
	
	public KeChengStudentMO getById(String id) throws LittleCatException
	{
		return keChengStudentDao.getById(id);
	}

	public List<KeChengStudentMO> getByKeCheng(String kecheng, String state, String key) throws LittleCatException
	{
		return keChengStudentDao.getByKeCheng(kecheng, state, key);
	}

	public List<KeChengStudentMO> getByStudent(String student, String state, String key) throws LittleCatException
	{
		return keChengStudentDao.getByStudent(student, state, key);
	}
	
	public KeChengStudentMO getByKeChengAndStudent(String kecheng, String student) throws LittleCatException
	{
		return keChengStudentDao.getByKeChengAndStudent(kecheng, student);
	}

	public void zanting(String id) throws LittleCatException
	{
		keChengStudentDao.zanting(id);
	}

	public void huifu(String id) throws LittleCatException
	{
		keChengStudentDao.huifu(id);
	}

	public void end(String id) throws LittleCatException
	{
		keChengStudentDao.end(id);
	}

	public String exists(String kecheng, String student) throws LittleCatException
	{
		return keChengStudentDao.exists(kecheng, student);
	}
}
