package com.littlecat.ims.common.consts;

/**
 * 数据库表名
 * 
 * @author amydady
 *
 */
public enum TableName
{

	SysParam("t_sys_param"),
	Menu("t_sys_menu"),
	Action("t_sys_action"),
	Role("t_sys_role"),
	SysOperator("t_sys_sysoperator"),
	
	XiaoQuArea("t_basic_xiaoquarea"),
	XiaoQu("t_basic_xiaoqu"),
	DicType("t_basic_dicType"),
	DicContent("t_basic_dicContent"),
	

	Student("t_student"),
	Kecheng("t_kecheng"),
	KeChengStudent("t_kecheng_student"),
	PayRecord("t_kecheng_payrecord"),
	
	
	
	//personal
	GouTongJiLu("t_personal_goutongjilu"),
	;

	private String name;

	TableName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
