package com.laudoecia.api.worklistes;

import javax.ejb.EJBException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDIUtils {

    public static Object lookup(String name) {
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
            return ctx.lookup(name);
        } catch (NamingException e) {
            throw new EJBException(e);
        } finally {
            if (ctx != null)
                try {
                    ctx.close();
                } catch (NamingException e) {}
        }
    }

}
