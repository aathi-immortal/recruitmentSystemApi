package com.ooselab.recruitmentsystemapi.userModule;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserBussinessLogic userBussinessLogic;

    @PostMapping("/login")
    public CompletableFuture<User> UserLogin(@RequestBody User user) {

        return userBussinessLogic.LoginUser(user);

    }

    @PostMapping("/registration")
    public CompletableFuture<User> Registration(@RequestBody User user) {

        return userBussinessLogic.Registration(user);

    }

    @GetMapping("/users")
    public CompletableFuture<List<User>> users() {
        return userBussinessLogic.getUsers();
    }

    @GetMapping("/testing")
    public String testing() {
        return "success";
    }

    @PostMapping("/addJob")
    public Message addJob(@RequestBody Job job) {
        System.out.println("vanakkam ---------------------------------------------------------------------");
        return userBussinessLogic.addJob(job);
    }

    @PostMapping("/getJob")
    public List<Job> getCreatedJob(@RequestBody int userId) {
        return userBussinessLogic.getCreatedJob(userId);
    }

    @PostMapping("/removeJob")
    public Message removeJob(@RequestBody int jobId) {
        return userBussinessLogic.removeJob(jobId);
    }

    @PostMapping("/getAllJobs")
    public List<Job> getAllJobs(@RequestBody int user_id) {
        return userBussinessLogic.getAllJobs(user_id);
    }

    @PostMapping("/applyJob")
    public Message applyJob(@RequestBody UserJob job) {
        System.out.println("user id " + job.user_id);
        System.out.println("job Id " + job.job_id);
        return userBussinessLogic.applyJob(job);
    }

    @PostMapping("/getMyJobs")
    public List<Job> getAppliedJobs(@RequestBody int user_id) {
        return userBussinessLogic.getAppliedJobs(user_id);
    }

    @PostMapping("getRegisteredUsers")
    public List<AbstartUser> getRegUsers(@RequestBody int jobId) {
        return userBussinessLogic.getRegUsers(jobId);
    }

}
