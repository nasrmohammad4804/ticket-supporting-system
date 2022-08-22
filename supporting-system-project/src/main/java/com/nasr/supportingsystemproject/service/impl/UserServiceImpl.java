package com.nasr.supportingsystemproject.service.impl;

import com.nasr.supportingsystemproject.base.service.impl.BaseServiceImpl;
import com.nasr.supportingsystemproject.domain.User;
import com.nasr.supportingsystemproject.domain.enumeration.UserType;
import com.nasr.supportingsystemproject.dto.UserDto;
import com.nasr.supportingsystemproject.exception.ModelNotFoundException;
import com.nasr.supportingsystemproject.exception.UserNotValidException;
import com.nasr.supportingsystemproject.repository.UserRepository;
import com.nasr.supportingsystemproject.service.RoleService;
import com.nasr.supportingsystemproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseServiceImpl<User, Long, String, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public void saveAll(Iterable<User> users) {
        repository.saveAll(users);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public UserDto getUserProfileByEmail(String email) {
        return repository.findUserProfileByEmail(email)
                .orElseThrow(() -> new ModelNotFoundException("dont find any user with email : " + email));
    }

    @Override
    public String getUserType(String email) {
        return repository.findUserTypeByEmail(email);
    }

    @Override
    @Transactional
    public void activateUser(Long userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new ModelNotFoundException("dont find any user with id : " + userId));

        if (user.isEnable()) return;

        user.setEnable(true);
    }

    @Override
    @Transactional
    public User saveOrUpdate(User user) {
        Optional<User> optionalUser =
                repository.findByEmail(user.getEmail());

        if (optionalUser.isPresent())
            throw new UserNotValidException("this user already exists with email : " + user.getEmail());

        UserType userType = user.getUserType();
        userType.setRoleService(roleService);

        User userEntity = userType.getUser(user);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return super.saveOrUpdate(userEntity);
    }
}
