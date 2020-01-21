package com.littlecat.ims.kecheng.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.ims.common.consts.PaiKeCycleType;
import com.littlecat.ims.common.consts.StudentKeChengState;
import com.littlecat.ims.kecheng.dao.KeChengBiaoDao;
import com.littlecat.ims.kecheng.model.KeChengBiaoMO;
import com.littlecat.ims.kecheng.model.KeChengMO;
import com.littlecat.ims.kecheng.model.KeChengStudentMO;
import com.littlecat.ims.student.business.StudentBusiness;

@Component
@Transactional
public class KeChengBiaoBusiness
{
	@Autowired
	private KeChengBiaoDao keChengBiaoDao;

	@Autowired
	private KeChengBusiness keChengBusiness;

	@Autowired
	private KeChengStudentBusiness keChengStudentBusiness;

	@Autowired
	private StudentBusiness studentBusiness;

	private static final String KECHENGBIAO_RULE_CHECK_ERROR = "KECHENGBIAO_RULE_CHECK_ERROR";

	public void delete(String id) throws LittleCatException
	{
		keChengBiaoDao.delete(id);
	}

	public String add(KeChengBiaoMO mo) throws LittleCatException
	{
		check(mo);

		return keChengBiaoDao.add(mo);
	}

	public KeChengBiaoMO getById(String id) throws LittleCatException
	{
		return keChengBiaoDao.getById(id);
	}

	public List<KeChengBiaoMO> getList(String kecheng, String teacher, String student) throws LittleCatException
	{
		return keChengBiaoDao.getList(kecheng, teacher, student);
	}

	public void check(KeChengBiaoMO kechengbiaoMO) throws LittleCatException
	{
		KeChengMO kechengMO = keChengBusiness.getById(kechengbiaoMO.getKecheng());
		teacherCheck(kechengMO.getTeacher(), kechengbiaoMO);

		List<KeChengStudentMO> kechengstudentList = keChengStudentBusiness.getByKeCheng(kechengbiaoMO.getKecheng(), StudentKeChengState.zhengchang.getCode(), "");
		studentCheck(kechengstudentList, kechengbiaoMO);
	}

	public void teacherCheck(String teacher, KeChengBiaoMO kechengbiaoMO) throws LittleCatException
	{
		List<KeChengBiaoMO> kebiaoListByTeacher = keChengBiaoDao.getList("", teacher, "");

		String begintimeHour1 = kechengbiaoMO.getTimebeginhour();
		String begintimemin1 = kechengbiaoMO.getTimebeginmin();
		String begintime1 = begintimeHour1 + begintimemin1;

		String endtimeHour1 = kechengbiaoMO.getTimeendhour();
		String endtimemin1 = kechengbiaoMO.getTimeendmin();
		String endtime1 = endtimeHour1 + endtimemin1;

		if (PaiKeCycleType.周几.getCode().equals(kechengbiaoMO.getCycle()))
		{
			for (KeChengBiaoMO kebiaoMO : kebiaoListByTeacher)
			{
				if (!kechengbiaoMO.getWeekday().equals(kebiaoMO.getWeekday()))
				{
					continue;
				}

				String begintimeHour2 = kebiaoMO.getTimebeginhour();
				String begintimemin2 = kebiaoMO.getTimebeginmin();
				String begintime2 = begintimeHour2 + begintimemin2;

				String endtimeHour2 = kebiaoMO.getTimeendhour();
				String endtimemin2 = kebiaoMO.getTimeendmin();
				String endtime2 = endtimeHour2 + endtimemin2;

				if (!(begintime1.compareTo(endtime2) >= 0 || begintime2.compareTo(endtime1) >= 0))
				{
					String msg = "教师时间冲突：";
					msg += keChengBusiness.getById(kebiaoMO.getKecheng()).getName() + "|";
					msg += kebiaoMO.getWeekdayName() + "|";
					msg += begintimeHour2 + ":" + begintimemin2 + "-" + endtimeHour2 + ":" + endtimemin2;

					throw new LittleCatException(KECHENGBIAO_RULE_CHECK_ERROR, msg);
				}
			}
		}
	}

	public void studentCheck(List<KeChengStudentMO> kechengstudentList, KeChengBiaoMO kechengbiaoMO) throws LittleCatException
	{
		String begintimeHour1 = kechengbiaoMO.getTimebeginhour();
		String begintimemin1 = kechengbiaoMO.getTimebeginmin();
		String begintime1 = begintimeHour1 + begintimemin1;

		String endtimeHour1 = kechengbiaoMO.getTimeendhour();
		String endtimemin1 = kechengbiaoMO.getTimeendmin();
		String endtime1 = endtimeHour1 + endtimemin1;

		if (PaiKeCycleType.周几.getCode().equals(kechengbiaoMO.getCycle()))
		{
			for (KeChengStudentMO studentMO : kechengstudentList)
			{
				List<KeChengBiaoMO> kebiaoListByStudent = keChengBiaoDao.getList("", "", studentMO.getStudent());

				for (KeChengBiaoMO kebiaoMO : kebiaoListByStudent)
				{
					if (!kechengbiaoMO.getWeekday().equals(kebiaoMO.getWeekday()))
					{
						continue;
					}

					String begintimeHour2 = kebiaoMO.getTimebeginhour();
					String begintimemin2 = kebiaoMO.getTimebeginmin();
					String begintime2 = begintimeHour2 + begintimemin2;

					String endtimeHour2 = kebiaoMO.getTimeendhour();
					String endtimemin2 = kebiaoMO.getTimeendmin();
					String endtime2 = endtimeHour2 + endtimemin2;

					if (!(begintime1.compareTo(endtime2) >= 0 || begintime2.compareTo(endtime1) >= 0))
					{
						String msg = "学生时间冲突：";
						msg += studentBusiness.getById(studentMO.getStudent()).getName() + "|";
						msg += keChengBusiness.getById(kebiaoMO.getKecheng()).getName() + "|";
						msg += kebiaoMO.getWeekdayName() + "|";
						msg += begintimeHour2 + ":" + begintimemin2 + "-" + endtimeHour2 + ":" + endtimemin2;

						throw new LittleCatException(KECHENGBIAO_RULE_CHECK_ERROR, msg);
					}
				}

			}

		}
	}

}
