package com.littlecat.ims.common.consts;

/**
 * 员工工作类型
 * 
 * @author amydady
 *
 */
public enum StaffWorkType
{
	全职("1"), 兼职("2"), 
	
	;

	private String code;

	StaffWorkType(String code)
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
		for(StaffWorkType elem:StaffWorkType.values())
		{
			if(elem.code.equals(code))
			{
				return elem.name();
			}
		}
		
		return "";
	}
}
