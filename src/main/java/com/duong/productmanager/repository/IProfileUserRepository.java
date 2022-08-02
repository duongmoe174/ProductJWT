package com.duong.productmanager.repository;

import com.duong.productmanager.entity.ProfileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProfileUserRepository extends JpaRepository<ProfileUser, Long> {
    Optional<ProfileUser> findByAppUser_Username (String username);
}
