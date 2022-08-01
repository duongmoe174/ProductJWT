package com.duong.productmanager.service.role;

import com.duong.productmanager.entity.AppRole;
import com.duong.productmanager.entity.ERole;
import com.duong.productmanager.repository.IAppRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private IAppRoleRepository roleRepository;

    @Override
    public Iterable<AppRole> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<AppRole> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public AppRole save(AppRole appRole) {
        return roleRepository.save(appRole);
    }

    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }


    @Override
    public Optional<AppRole> findByName(ERole name) {
        return roleRepository.findByName(name);
    }
}
