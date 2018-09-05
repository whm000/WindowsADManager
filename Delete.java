/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LdapOper;

import javax.naming.NamingException;
import javax.naming.ldap.LdapContext;

public class Delete {

    /**
     * @explain delete a user
     * @throws NamingException
     */
    public void deleteUser(String uid) throws NamingException {
        /**
         * ���ӵ�LDAP
         */
        LdapContext ctx = new ConnectLDAP().connectToLDAP();

        String userName = uid;

        /**
         * �û���·��
         */
        //String userDN = "CN=" + userName + "," + "CN=Users,DC=demo,DC=com";
        String userDN = "CN=" + userName + "," + "OU=aaa,DC=demo,DC=com";
        ctx.destroySubcontext(userDN);

        ctx.close();

        System.out.println("<<<:[DELETE success]:>>>");
    }

}
