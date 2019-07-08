package com.littlecat.ims.common.consts;

/**
 * 字典类型常量
 * 
 * @author amydady
 *
 */
public enum DicType
{
	xuexiao("1"), nianji("2"), banji("3"),xiaoqu("4"),
	;

	private String code;

	DicType(String code)
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
}
