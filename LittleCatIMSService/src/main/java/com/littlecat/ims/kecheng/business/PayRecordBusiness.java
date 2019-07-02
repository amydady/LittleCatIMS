package com.littlecat.ims.kecheng.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.common.consts.StudentKeChengState;
import com.littlecat.ims.kecheng.dao.PayRecordDao;
import com.littlecat.ims.kecheng.model.KeChengStudentMO;
import com.littlecat.ims.kecheng.model.PayRecordMO;

@Component
@Transactional
public class PayRecordBusiness
{
	@Autowired
	private PayRecordDao payRecordDao;

	@Autowired
	private KeChengStudentBusiness keChengStudentBusiness;

	public void modify(PayRecordMO mo) throws LittleCatException
	{
		payRecordDao.modify(mo);
	}

	public String add(PayRecordMO mo) throws LittleCatException
	{
		KeChengStudentMO keChengStudentMO = new KeChengStudentMO();
		
		keChengStudentMO.setKecheng(mo.getKecheng());
		keChengStudentMO.setStudent(mo.getStudent());
		keChengStudentMO.setRemaintimes(mo.getTimes());
		keChengStudentMO.setState(StudentKeChengState.zhengchang.getCode());

		keChengStudentBusiness.add(keChengStudentMO);
		return payRecordDao.add(mo);
	}

	public PayRecordMO getById(String id) throws LittleCatException
	{
		return payRecordDao.getById(id);
	}

	public List<PayRecordMO> getList(String studentId, String studentName) throws LittleCatException
	{
		return payRecordDao.getList(studentId, studentName);
	}

}
