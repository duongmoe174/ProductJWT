package com.duong.productmanager.repository;

import com.duong.productmanager.entity.ProfileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileUserRepository extends JpaRepository<ProfileUser, Long> {
    ProfileUser findByAppUser_Username (String username);
}
