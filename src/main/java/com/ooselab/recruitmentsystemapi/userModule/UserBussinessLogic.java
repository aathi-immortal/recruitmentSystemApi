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

    public String addJob(Job job);

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
        user.message = "user not found";

        user.isCompany = false;
        if (userRepo.userExists(user)) {
            user.message = "success";
            user.id = userRepo.getUserId(user);
        }
        if (userRepo.isAdmin(user)) {
            user.isCompany = true;
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

    @Override
    public String addJob(Job job) {
        return userRepo.addJob(job);
    }

    public List<Job> getCreatedJob(int userId) {
        return userRepo.getCreatedJobs(userId);
    }

    public String removeJob(int jobId) {

        return userRepo.removeJob(jobId);
    }

}
