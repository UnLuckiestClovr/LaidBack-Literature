package store.books.Mong_DAL.controller;
import org.springframework.web.bind.annotation.*;

import store.books.Mong_DAL.business.UserServicePortal;


import store.books.Mong_DAL.model.UpdateRequest;
import store.books.Mong_DAL.model.User;

import java.util.ArrayList;

import static store.books.Mong_DAL.business.UserServicePortal.*;

@RestController
@RequestMapping("/lb-literature")
public class UserController {


    @RequestMapping(path = "/find-user",method = RequestMethod.GET)
    public ArrayList<User>finUsername(@PathVariable String username){return UserServicePortal.findUserByUsername(username);}

    @RequestMapping(path = "/get-user",method = RequestMethod.GET)
    public ArrayList<User>getUsers(){return UserServicePortal.getAllUsers();}

    @RequestMapping(path = "/find-user-account-state",method = RequestMethod.GET)
    public ArrayList<User>findUserByAccountState(@PathVariable String stateSearch){return findUserByAccountStatus(stateSearch);}

    @RequestMapping(path = "/create-user",method = RequestMethod.POST)
    public void createUser(@RequestBody String addUser){UserServicePortal.createUserEntry(addUser);}

    @RequestMapping(path="/update-user", method=RequestMethod.PATCH)
    public void updateUser(@RequestBody UpdateRequest obj){updateUserEntry(obj);
    }

    @RequestMapping(path= "/delete-user",method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody String username){deleteUserEntry(username);}

}
