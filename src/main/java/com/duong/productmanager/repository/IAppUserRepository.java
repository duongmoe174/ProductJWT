package com.duong.productmanager.repository;

import com.duong.productmanager.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IAppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String name);

    Boolean existsByUsername(String name);

    Boolean existsByEmail(String email);
}
