package dev.yavuztas.boilerplate.springbootwebservice.service;

import dev.yavuztas.boilerplate.springbootwebservice.domain.Item;
import dev.yavuztas.boilerplate.springbootwebservice.domain.User;
import dev.yavuztas.boilerplate.springbootwebservice.exception.UsernameNotFoundException;
import dev.yavuztas.boilerplate.springbootwebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserItemService implements IUserItemService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String username) throws UsernameNotFoundException {
        if (username == null || username.isEmpty()) {
            throw new UsernameNotFoundException("");
        }
        Optional<User> hasUser = userRepository.findByUsername(username.trim());
        return hasUser.orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public List<Item> getUserItems(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        return userRepository.getUserItems(user.getUsername());
    }

}
