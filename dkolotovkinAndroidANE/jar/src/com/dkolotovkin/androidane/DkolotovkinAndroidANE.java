package com.dkolotovkin.androidane;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class DkolotovkinAndroidANE implements FREExtension 
{
	public static FREContext context;

	@Override
	public FREContext createContext(String arg0) 
	{
		context = new DkolotovkinAndroidANEContext();
		return context;
	}

	@Override
	public void dispose() 
	{
	}

	@Override
	public void initialize() 
	{
	}
}
