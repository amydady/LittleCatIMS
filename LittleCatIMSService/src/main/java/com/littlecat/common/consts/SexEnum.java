package com.littlecat.common.consts;

/**
 * 性别
 * 
 * @author amydady
 *
 */
public enum SexEnum
{
	male("男"), // 男
	female("女"); // 女

	private String displayName;

	SexEnum(String displayName)
	{
		this.setDisplayName(displayName);
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}
}
