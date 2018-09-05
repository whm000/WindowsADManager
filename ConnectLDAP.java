/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LdapOper;

/**
 *
 * @author Administrator
 */
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class ConnectLDAP {
	
	/**
	 * @explain get LdapContext from a account
	 * @author WANGJUN
	 * @return LdapContext
	 * @throws NamingException
	 */
	public LdapContext connectToLDAP() throws NamingException {

		Hashtable<String, String> HashEnv = new Hashtable<String, String>();
		String adminName = "demo\\administrator";
		String adminPassword = "micwang";
		String ldapURL = "ldap://192.168.150.181:389";

		HashEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple");// "none","simple","strong"
		HashEnv.put(Context.SECURITY_PRINCIPAL, adminName);
		HashEnv.put(Context.SECURITY_CREDENTIALS, adminPassword);
		HashEnv.put(Context.PROVIDER_URL, ldapURL);

		LdapContext ctx = new InitialLdapContext(HashEnv, null);
		System.out.println("<<<:[connect success]:>>>");
		return ctx;
	}
}

