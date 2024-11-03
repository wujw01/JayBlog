/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.jay.modules.authc;

import com.jay.modules.authc.vo.AccountProfile;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

public class AccountAuthenticationInfo extends SimpleAuthenticationInfo{
    private static final long serialVersionUID = 3405356595200877071L;
    
    private AccountProfile profile;

    public AccountAuthenticationInfo(){
    }
    
    public AccountAuthenticationInfo(Object principal, Object credentials, String realmName){
        super(principal, credentials, realmName);
    }

	public AccountProfile getProfile() {
		return profile;
	}

	public void setProfile(AccountProfile profile) {
		this.profile = profile;
	}

}
