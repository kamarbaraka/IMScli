package com.kamar.imscli.role.proxy;

import com.kamar.imscli.role.exception.RoleException;

/**
 * role creation proxy.
 * @author kamar baraka.*/

public interface RoleCreationProxy {

    void createRole(String authority) throws RoleException;
}
