package com.dkolotovkin.androidane.commands;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.OperationCanceledException;
import android.os.Bundle;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.dkolotovkin.androidane.DkolotovkinAndroidANE;

public class GetAuthTokenCommand implements FREFunction 
{
	String AUTH_TOKEN_PROFILE = "oauth2:https://www.googleapis.com/auth/userinfo.profile";
	
	@Override
	public FREObject call(FREContext context, FREObject[] args) 
	{
		try
		{
			String accountName = args[0].getAsString();
			AccountManager accountManager  = AccountManager.get(context.getActivity());
		    Account[] accounts = accountManager.getAccountsByType("com.google");
		    Account activeAccount = null;
		    for (int i = 0; i < accounts.length; i++) 
		    {
		    	if(accountName.equals(accounts[i].name))
		    	{
		    		activeAccount = accounts[i];
		    		break;
		    	}
		    }
		    
		    if(activeAccount != null)
		    {
		    	String authToken = null;
		    	try
		    	{
		    		AccountManagerFuture<Bundle> accountManagerFuture = accountManager.getAuthToken(activeAccount, AUTH_TOKEN_PROFILE, null, context.getActivity(), null, null);
		    		Bundle authTokenBundle = accountManagerFuture.getResult();
		    		authToken = authTokenBundle.getString(AccountManager.KEY_AUTHTOKEN).toString();
		    	}
		    	catch(Throwable e)
		    	{
		    	}
		    	
		    	accountManager.invalidateAuthToken("com.google", authToken);
		    	accountManager.getAuthToken(activeAccount, AUTH_TOKEN_PROFILE, null, context.getActivity(), new OnTokenAcquired(), null);
		    	
		    	return null;
		    }
		    else
		    {
		    	return null;
		    }
	    }
		catch(Throwable e)
	    {
	    	return null;
	    }
	}
	
	private class OnTokenAcquired implements AccountManagerCallback<Bundle> 
	{
		@Override
		public void run(AccountManagerFuture<Bundle> result) 
		{
			try
			{
				String token = result.getResult().getString(AccountManager.KEY_AUTHTOKEN);
				DkolotovkinAndroidANE.context.dispatchStatusEventAsync("ON_AUTH_TOKEN_ACQUIRED", token);
			}
			catch(OperationCanceledException e)
			{
			}
			catch (Exception e)
			{
			}
		}
	}
}