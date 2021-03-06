package com.littlecat.ims.common.consts;

/**
 * 学生与课程关系状态
 * 
 * @author amydady
 *
 */
public enum StudentKeChengState
{
	zhengchang("1"), zanting("2"), jieshu("3"),
	;

	private String code;

	StudentKeChengState(String code)
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
