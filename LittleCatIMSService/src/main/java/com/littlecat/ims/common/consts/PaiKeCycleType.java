package com.littlecat.ims.common.consts;

/**
 * 排课周期类型常量
 * 
 * @author amydady
 *
 */
public enum PaiKeCycleType
{
	周几("1"), 日期段("2"), 具体日期("3"),
	;

	private String code;

	PaiKeCycleType(String code)
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
		for(PaiKeCycleType state:PaiKeCycleType.values())
		{
			if(state.code.equals(code))
			{
				return state.name();
			}
		}
		
		return "";
	}
}
