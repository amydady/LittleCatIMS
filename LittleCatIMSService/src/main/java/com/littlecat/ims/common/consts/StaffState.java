package com.littlecat.ims.common.consts;

/**
 * 员工状态
 * 
 * @author amydady
 *
 */
public enum StaffState
{
	试用中("1"), 正式员工("2"), 已离职("3"), 
	
	;

	private String code;

	StaffState(String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
	
	public static String getNameByCode(String code)
	{
		for(StaffState elem:StaffState.values())
		{
			if(elem.code.equals(code))
			{
				return elem.name();
			}
		}
		
		return "";
	}
}
