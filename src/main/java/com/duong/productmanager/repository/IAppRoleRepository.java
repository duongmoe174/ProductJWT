package com.duong.productmanager.repository;

import com.duong.productmanager.entity.AppRole;
import com.duong.productmanager.entity.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAppRoleRepository extends JpaRepository<AppRole, Long> {
    Optional<AppRole> findByName(ERole name);
}
