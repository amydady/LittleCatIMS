package com.littlecat.common.consts;

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
	

	Student("t_student"),
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
