package com.dkolotovkin.androidane.commands;

import android.accounts.Account;
import android.accounts.AccountManager;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class GetAccountsCommand implements FREFunction 
{
	@Override
	public FREObject call(FREContext context, FREObject[] args) 
	{
		try{
			AccountManager accountManager  = AccountManager.get(context.getActivity());
		    Account[] accounts = accountManager.getAccountsByType("com.google");
		    
		    String accountsNames = "";
		    for (int i = 0; i < accounts.length; i++) 
		    {
		    	if(i > 0)
		    		accountsNames += "|";
		    	
		        accountsNames += accounts[i].name;
		    }
		    
	    	return FREObject.newObject(accountsNames);
	    }
		catch(Throwable e)
		{
	    	return null;
	    }
	}
}
