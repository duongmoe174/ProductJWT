package com.duong.productmanager.service.profileusers;

import com.duong.productmanager.entity.ProfileUser;
import com.duong.productmanager.repository.IProfileUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProfileUsersService implements IProfileUsersService{
    @Autowired
    private IProfileUserRepository profileUserRepository;

    @Override
    public Iterable<ProfileUser> findAll() {
        return profileUserRepository.findAll();
    }

    @Override
    public Optional<ProfileUser> findById(Long id) {
        return profileUserRepository.findById(id);
    }

    @Override
    public ProfileUser save(ProfileUser profileUser) {
        return profileUserRepository.save(profileUser);
    }

    @Override
    public void remove(Long id) {
        profileUserRepository.deleteById(id);
    }

    @Override
    public ProfileUser findByAppUser_Username(String username) {
        return profileUserRepository.findByAppUser_Username(username);
    }
}
