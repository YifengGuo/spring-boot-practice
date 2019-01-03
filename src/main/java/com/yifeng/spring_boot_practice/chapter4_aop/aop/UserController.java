package com.yifeng.spring_boot_practice.chapter4_aop.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by guoyifeng on 12/30/18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/print")
    @ResponseBody
    public User printUser(Long id, String username, String note) {
        User user = new User();
        user.setId(id);
        user.setNote(note);
        user.setUsername(username);
        userService.printUser(user);  // if user == null, afterThrowing() will be invoked
        return user;
    }

    @RequestMapping("/vp")
    @ResponseBody
    public User validateAndPrint(Long id, String username, String note) {
        User user = new User();
        user.setUsername(username);
        user.setId(id);
        user.setNote(note);
        UserValidator validator = (UserValidator) userService;  // dynamic proxy will pass all interfaces of target and make it possible
                                                                // for interfaces (targets) cast between each other
        if (validator.validate(user)) {
            userService.printUser(user);
        }
        return user;
    }
}
