package com.littlecat.ims.staff.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.littlecat.cbb.common.Consts;
import com.littlecat.cbb.exception.LittleCatException;
import com.littlecat.cbb.utils.StringUtil;
import com.littlecat.cbb.utils.UUIDUtil;
import com.littlecat.ims.common.consts.TableName;
import com.littlecat.ims.staff.model.StaffMO;

@Component
public class StaffDao
{
	private final String TABLE_NAME = TableName.Staff.getName();

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public String add(StaffMO mo) throws LittleCatException
	{
		if (StringUtil.isEmpty(mo.getId()))
		{
			mo.setId(UUIDUtil.createUUID());
		}

		String sql = "insert into " + TABLE_NAME + "(id,name,mobile,idcard,worktype,sex,age,biyexuexiao,biyeshijian,xueli,zhuanye,techangjirongyu,joindate,"
				+ "shiyongqi,zhuanzhengdate,shiyonggongzi,zhengshigongzi,keshigongzi,baoxianxinxi,remark,state) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getId(), mo.getName(),mo.getMobile(),mo.getIdcard(), mo.getWorktype(), mo.getSex(), mo.getAge(), mo.getBiyexuexiao(), mo.getBiyeshijian(), mo.getXueli(), mo.getZhuanye(), mo.getTechangjirongyu(),
					mo.getJoindate(), mo.getShiyongqi(), mo.getZhuanzhengdate(), mo.getShiyonggongzi(), mo.getZhengshigongzi(), mo.getKeshigongzi(), mo.getBaoxianxinxi(), mo.getRemark(), mo.getState() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}

		return mo.getId();
	}

	public void modify(StaffMO mo) throws LittleCatException
	{
		String sql = "update " + TABLE_NAME + " set name = ?,mobile = ?,idcard = ?,worktype = ?,sex = ?,age = ?,biyexuexiao = ?,biyeshijian = ?,xueli = ?,zhuanye = ?,techangjirongyu = ?,joindate = ?,shiyongqi = ?,zhuanzhengdate = ?,shiyonggongzi = ?,zhengshigongzi = ?,keshigongzi = ?,baoxianxinxi = ?,remark = ?,state = ?,leavedate = ? where id = ?";

		try
		{
			jdbcTemplate.update(sql, new Object[] { mo.getName(),mo.getMobile(),mo.getIdcard(), mo.getWorktype(), mo.getSex(), mo.getAge(), mo.getBiyexuexiao(), mo.getBiyeshijian(), mo.getXueli(), mo.getZhuanye(), mo.getTechangjirongyu(),
					mo.getJoindate(), mo.getShiyongqi(), mo.getZhuanzhengdate(), mo.getShiyonggongzi(), mo.getZhengshigongzi(), mo.getKeshigongzi(), mo.getBaoxianxinxi(), mo.getRemark(), mo.getState(), mo.getLeavedate(),mo.getId() });
		}
		catch (DataAccessException e)
		{
			throw new LittleCatException(Consts.ERROR_CODE_DATAACCESSEXCEPTION, e.getMessage(), e);
		}
	}

	public StaffMO getById(String id) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*  from ").append(TABLE_NAME).append(" a ")
				.append(" where a.id = ?");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[] { id }, new StaffMO.MOMapper());
	}

	public List<StaffMO> getList(String key, String state) throws LittleCatException
	{
		StringBuilder sql = new StringBuilder()
				.append("select a.*  from ").append(TABLE_NAME).append(" a ");

		if (StringUtil.isNotEmpty(key))
		{
			sql.append(" where a.name like '%" + key + "%'");
		}

		if (StringUtil.isNotEmpty(state))
		{
			sql.append(" where a.state = '" + state + "'");
		}

		sql.append(" order by a.createTime desc ");

		return jdbcTemplate.query(sql.toString(), new StaffMO.MOMapper());
	}
}
