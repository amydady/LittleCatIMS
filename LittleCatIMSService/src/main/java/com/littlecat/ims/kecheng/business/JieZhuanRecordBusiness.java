package com.littlecat.ims.kecheng.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.ims.common.consts.StudentKeChengState;
import com.littlecat.ims.kecheng.dao.JieZhuanRecordDao;
import com.littlecat.ims.kecheng.model.JieZhuanRecordMO;
import com.littlecat.ims.kecheng.model.KeChengStudentMO;

@Component
@Transactional
public class JieZhuanRecordBusiness
{
	@Autowired
	private JieZhuanRecordDao jieZhuanRecordDao;

	@Autowired
	private KeChengStudentBusiness keChengStudentBusiness;

	public String add(JieZhuanRecordMO mo) throws LittleCatException
	{
		// 源课程课时消减
		KeChengStudentMO kechengs = keChengStudentBusiness.getByKeChengAndStudent(mo.getKechengs(), mo.getStudent());
		kechengs.setRemaintimes(kechengs.getRemaintimes() - mo.getTimes());
		keChengStudentBusiness.modify(kechengs);

		// 目标课程课时增加
		String kechengstudentid = keChengStudentBusiness.exists(mo.getKechengd(), mo.getStudent());
		if (StringUtil.isEmpty(kechengstudentid))
		{
			KeChengStudentMO kechengd = new KeChengStudentMO();
			kechengd.setStudent(mo.getStudent());
			kechengd.setKecheng(mo.getKechengd());
			kechengd.setRemaintimes(mo.getTimes());
			kechengd.setState(StudentKeChengState.zhengchang.getCode());
			keChengStudentBusiness.add(kechengd);
		}
		else
		{
			KeChengStudentMO kechengd = keChengStudentBusiness.getById(kechengstudentid);
			kechengd.setRemaintimes(kechengd.getRemaintimes() + mo.getTimes());
			keChengStudentBusiness.modify(kechengd);
		}

		return jieZhuanRecordDao.add(mo);
	}

	public List<JieZhuanRecordMO> getList(String kechengs, String kechengd, String student, String key) throws LittleCatException
	{
		return jieZhuanRecordDao.getList(kechengs, kechengd, student, key);
	}
}
