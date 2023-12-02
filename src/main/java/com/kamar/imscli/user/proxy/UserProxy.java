package com.kamar.imscli.user.proxy;

import com.kamar.imscli.user.data.dto.UserActivationDto;
import com.kamar.imscli.user.data.dto.UserLoginDto;
import com.kamar.imscli.user.data.dto.UserRegDto;
import com.kamar.imscli.user.exception.UserException;
import com.kamar.imscli.user.model.AppUser;
import com.kamar.imscli.role.model.Role;

import java.util.List;

/**
 * the user proxy contract.
 * @author kamar baraka.*/

public interface UserProxy {

    AppUser login(UserLoginDto userLoginDto) throws UserException;

    void  register(UserRegDto userRegDto) throws UserException;

    void activate(UserActivationDto userActivationDto) throws UserException;

    List<Role> getAuthorities() throws UserException;

    List<AppUser> getAllUsersWithAuthority(String authority) throws UserException;
}
