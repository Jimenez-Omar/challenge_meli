package com.mercadolibre.challenge.user.domain.services;

import com.mercadolibre.challenge.user.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getUserList();

    Optional<User> getUser(Long userId);

    Optional<User> updateUser(Long userId);

}
