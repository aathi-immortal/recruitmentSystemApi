package com.ooselab.recruitmentsystemapi.userModule;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * IUserBussinessLogic
 */
interface IUserBussinessLogic {

    public CompletableFuture<User> LoginUser(User user);

    public CompletableFuture<User> Registration(User user);

    public CompletableFuture<List<User>> getUsers();

    public Message addJob(Job job);

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
    public Message addJob(Job job) {
        return userRepo.addJob(job);
    }

    public List<Job> getCreatedJob(int userId) {
        return userRepo.getCreatedJobs(userId);
    }

    public Message removeJob(int jobId) {

        return userRepo.removeJob(jobId);
    }

    public List<Job> getAllJobs(int job) {
        return userRepo.getAllJobs(job);

    }

    public Message applyJob(UserJob job) {

        return userRepo.applyJob(job);
    }

    public List<Job> getAppliedJobs(int user_id) {
        return userRepo.getAppliedJob(user_id);

    }

    public List<AbstartUser> getRegUsers(int jobId) {
        return userRepo.getRegUsers(jobId);
    }

    public String uploadResume(ImageRequest file) {

        return userRepo.uploadResume(file);

    }

    public byte[] getResume(int userId) {
        return userRepo.getResume(userId);
    }

}
