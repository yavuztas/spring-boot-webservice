package dev.yavuztas.boilerplate.springbootwebservice.controller;

import dev.yavuztas.boilerplate.springbootwebservice.domain.Item;
import dev.yavuztas.boilerplate.springbootwebservice.service.IUserItemService;
import dev.yavuztas.boilerplate.springbootwebservice.view.UserItemsView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "user", description = "All endpoints about User")
@RequestMapping("/api/1.0")
@RestController
public class UserWebserviceController {

    @Autowired
    private IUserItemService userItemService;

    @ApiOperation("Get all user items")
    @GetMapping("/user/{username}")
    public UserItemsView userItems(@PathVariable String username){
        List<Item> userItems = userItemService.getUserItems(username);
        return new UserItemsView(username, userItems);
    }

}
