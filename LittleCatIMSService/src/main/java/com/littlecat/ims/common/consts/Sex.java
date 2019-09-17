package com.littlecat.ims.common.consts;

/**
 * 性别
 * 
 * @author amydady
 *
 */
public enum Sex
{
	男("1"), 女("2"), 
	
	;

	private String code;

	Sex(String code)
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
		for(Sex elem:Sex.values())
		{
			if(elem.code.equals(code))
			{
				return elem.name();
			}
		}
		
		return "";
	}
}
