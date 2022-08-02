package com.duong.productmanager.service.profileusers;

import com.duong.productmanager.entity.ProfileUser;
import com.duong.productmanager.service.IGeneralService;

import java.util.Optional;

public interface IProfileUsersService extends IGeneralService<ProfileUser> {
    Optional<ProfileUser> findByAppUser_Username (String username);
}
