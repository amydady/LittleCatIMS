package com.littlecat.ims.common.consts;

/**
 * 学历
 * 
 * @author amydady
 *
 */
public enum XueLi
{
	研究生("1"),本科("2"), 大专("3"), 
	
	;

	private String code;

	XueLi(String code)
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
		for(XueLi elem:XueLi.values())
		{
			if(elem.code.equals(code))
			{
				return elem.name();
			}
		}
		
		return "";
	}
}
