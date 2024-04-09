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
    public String addJob(@RequestBody Job job) {
        System.out.println("vanakkam ---------------------------------------------------------------------");
        return userBussinessLogic.addJob(job);
    }

    @PostMapping("/getJob")
    public List<Job> getCreatedJob(@RequestBody int userId) {
        return userBussinessLogic.getCreatedJob(userId);
    }
    @PostMapping("/removeJob")
    public String removeJob(@RequestBody int jobId)
    {
        return userBussinessLogic.removeJob(jobId);
    }
}
