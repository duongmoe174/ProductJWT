package com.duong.productmanager.service.profileusers;

import com.duong.productmanager.entity.ProfileUser;
import com.duong.productmanager.service.IGeneralService;

public interface IProfileUsersService extends IGeneralService<ProfileUser> {
    ProfileUser findByAppUser_Username (String username);
}
