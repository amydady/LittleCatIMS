package com.littlecat.ims.common.consts;

/**
 * 
 * 沟通记录状态
 * 
 * @author amydady
 *
 */
public enum GouTongJiLuState
{
	jinxingzhong("1"), guaqi("2"), yiwancheng("3"),
	;

	private String code;

	GouTongJiLuState(String code)
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
