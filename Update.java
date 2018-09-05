/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LdapOper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapContext;

public class Update {

    public void updateUser(String uid, String uname, String uphone) throws NamingException {

        LdapContext ctx = new ConnectLDAP().connectToLDAP();
        String userName = uid;
        // String userDN = "CN=" + userName + "," + "CN=Users,DC=demo,DC=com";
        String userDN = "CN=" + userName + "," + "OU=aaa,DC=demo,DC=com";

        Attributes attrs = new BasicAttributes(true);

        attrs.put("sn", uname);
        attrs.put("givenName", uname);
        attrs.put("displayName", uname);
        attrs.put("ipPhone", uphone);
        attrs.put("homePhone", uphone);

        ctx.modifyAttributes(userDN, DirContext.REPLACE_ATTRIBUTE, attrs);

        ctx.close();

        System.out.println("<<<:[UPDATE success]:>>>");
    }

}
