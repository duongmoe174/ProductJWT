package com.duong.productmanager.service.user;

import com.duong.productmanager.entity.AppUser;
import com.duong.productmanager.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<AppUser>, UserDetailsService {
    Optional<AppUser> findByUsername(String name);

    Boolean existsByUsername(String name);

    Boolean existsByEmail(String email);
}
