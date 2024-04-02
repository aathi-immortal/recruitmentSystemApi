package com.ooselab.recruitmentsystemapi.userModule;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * IUserBussinessLogic
 */
interface IUserBussinessLogic {

    public CompletableFuture<User> LoginUser(User user);

    public CompletableFuture<User> Registration(User user);

    public CompletableFuture<List<User>> getUsers();
}

@Component
public class UserBussinessLogic implements IUserBussinessLogic {

    @Autowired
    private UserRepo userRepo;

    @Override
    public CompletableFuture<User> LoginUser(User user) {

        // check is the user in the db
        // if not the return error
        // return User
        user.message = "success";
        if (!userRepo.userExists(user)) {
            user.message = "user not found";
        }

        return CompletableFuture.completedFuture(user);
    }

    @Override
    public CompletableFuture<User> Registration(User user) {

        // store the user in the db

        return CompletableFuture.completedFuture(userRepo.Save(user));
    }

    @Override
    public CompletableFuture<List<User>> getUsers() {
        return CompletableFuture.completedFuture(userRepo.getAllUser());
    }

}
