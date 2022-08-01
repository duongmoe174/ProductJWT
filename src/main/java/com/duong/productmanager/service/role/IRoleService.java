package com.duong.productmanager.service.role;

import com.duong.productmanager.entity.AppRole;
import com.duong.productmanager.entity.ERole;
import com.duong.productmanager.service.IGeneralService;

import java.util.Optional;

public interface IRoleService extends IGeneralService<AppRole> {
    Optional<AppRole> findByName(ERole name);
}
