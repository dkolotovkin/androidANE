package com.dkolotovkin.androidane;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.dkolotovkin.androidane.commands.GetAccountsCommand;
import com.dkolotovkin.androidane.commands.GetAuthTokenCommand;

public class DkolotovkinAndroidANEContext extends FREContext 
{

	@Override
	public void dispose() 
	{
	}

	@Override
	public Map<String, FREFunction> getFunctions() 
	{
		Map<String, FREFunction> map = new HashMap<String, FREFunction>();
		map.put("getAccounts", new GetAccountsCommand());
		map.put("getAuthToken", new GetAuthTokenCommand());
		
		return map;
	}
}
