/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LdapOper;

import java.util.Arrays;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.LdapContext;

public class Add {

    /**
     * add user to AD
     */
    public void addUser(String uid, String name, String homePhone) throws NamingException {

        LdapContext ctx = new ConnectLDAP().connectToLDAP();

        Attributes attrs = new BasicAttributes(true);
        Attribute objclass = new BasicAttribute("objectclass");
        Attribute pass = new BasicAttribute("userpassword");

        String newUserName = uid;

        /**
         * add password
         */
        pass.add("Test_123");

        /**
         * add ObjectClass
         */
        String[] attrObjectClassPerson = {"user", "organizationalPerson", "person", "top"};
        Arrays.sort(attrObjectClassPerson);
        for (String ocp : attrObjectClassPerson) {
            objclass.add(ocp);
        }

        /**
         * set attr
         */
        attrs.put(pass);
        attrs.put(objclass);

       // String userDN = "CN=" + newUserName + "," + "CN=Users,DC=demo,DC=com";
         String userDN = "CN=" + newUserName + "," + "OU=aaa,DC=demo,DC=com";
        // int UF_ACCOUNTDISABLE = 0x0002;
        int UF_PASSWD_NOTREQD = 0x0020;
        // int UF_PASSWD_CANT_CHANGE = 0x0040;
        int UF_NORMAL_ACCOUNT = 0x0200;
        int UF_DONT_EXPIRE_PASSWD = 0x10000;
        // int UF_PASSWORD_EXPIRED = 0x800000;

        attrs.put("sn", name);
        attrs.put("givenName", name);
        attrs.put("cn", newUserName);
        attrs.put("displayName", name);
        attrs.put("mail", "test@163.com");
        attrs.put("description", "test");
        attrs.put("userPrincipalName", "test@163.com");
        attrs.put("sAMAccountName", uid);
        attrs.put("msDS-SupportedEncryptionTypes", "0");
        attrs.put("facsimileTelephoneNumber", "test@163.com");
        attrs.put("pager", "****");
        attrs.put("ipPhone", homePhone);
        attrs.put("homePhone", homePhone);
        attrs.put("mobile", "***********");
        attrs.put("userAccountControl",
                Integer.toString(UF_DONT_EXPIRE_PASSWD + UF_NORMAL_ACCOUNT + UF_PASSWD_NOTREQD));

        ctx.createSubcontext(userDN, attrs);

        ctx.close();

        System.out.println("<<<:[ADD success]:>>>");
    }

}
