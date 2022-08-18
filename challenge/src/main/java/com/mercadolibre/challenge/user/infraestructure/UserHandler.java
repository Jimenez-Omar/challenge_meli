package com.mercadolibre.challenge.user.infraestructure;

import com.mercadolibre.challenge.user.domain.models.User;
import com.mercadolibre.challenge.user.domain.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {

    private UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    private ResponseEntity<List<User>> listCategory(){

        List<User> list = userService.getUserList();

        try {
            return ResponseEntity.created(new URI("user/list/all")).body(list);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
