package com.duong.productmanager.service.user;

import com.duong.productmanager.entity.AppUser;
import com.duong.productmanager.entity.dto.UserPrincipal;
import com.duong.productmanager.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IAppUserRepository userRepository;

    @Override
    public Iterable<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<AppUser> findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public Boolean existsByUsername(String name) {
        return userRepository.existsByUsername(name);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return UserPrincipal.build(userOptional.get());
        }
        return null;
    }
}
