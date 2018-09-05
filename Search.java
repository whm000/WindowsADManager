/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LdapOper;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;

public class Search {

    public void SearchUser() throws NamingException {
        try {

            LdapContext ctx = new ConnectLDAP().connectToLDAP();
            SearchControls searchCtls = new SearchControls();
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String searchFilter = "objectClass=organizationalPerson";
            String searchBase = "CN=Users,DC=wilcom,DC=com,DC=cn";
            /**
             * ���Ʒ��صĽ��
             */
            String returnedAtts[] = {"memberOf"};
            searchCtls.setReturningAttributes(returnedAtts);
            NamingEnumeration<SearchResult> answer = ctx.search(searchBase, searchFilter, searchCtls);

            if (answer == null) {
                System.out.println("Have NO such user!");
            }

            while (answer.hasMoreElements()) {
                SearchResult sr = (SearchResult) answer.next();
                System.out.println("<<<::[" + sr.getName() + "]::>>>>");
            }
            ctx.close();

        } catch (NamingException e) {
            e.printStackTrace();
            System.err.println("Problem searching directory: " + e);

        }

    }

}
