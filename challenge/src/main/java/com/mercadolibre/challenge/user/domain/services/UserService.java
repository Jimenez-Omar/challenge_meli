package com.mercadolibre.challenge.user.domain.services;

import com.mercadolibre.challenge.user.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IUserService{

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUserList() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(Long userId) {

        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> updateUser(Long userId) {

        Optional<User> userFound = userRepository.findById(userId);

               return userFound.flatMap(usr->{
                    usr.setTotal_prestamos(usr.getTotal_prestamos()+1);
                    userRepository.saveAndFlush(usr);

                    return Optional.of(usr);
                });
    }
}
