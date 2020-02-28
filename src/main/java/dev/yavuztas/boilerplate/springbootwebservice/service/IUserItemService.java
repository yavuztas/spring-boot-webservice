package dev.yavuztas.boilerplate.springbootwebservice.service;

import dev.yavuztas.boilerplate.springbootwebservice.exception.UsernameNotFoundException;
import dev.yavuztas.boilerplate.springbootwebservice.domain.Item;
import dev.yavuztas.boilerplate.springbootwebservice.domain.User;

import java.util.List;

public interface IUserItemService {

    User getUser(String username) throws UsernameNotFoundException;

    List<Item> getUserItems(String username) throws UsernameNotFoundException;

}
