package com.ooselab.recruitmentsystemapi.userModule;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class User {
    public int id;
    public String userName;
    public String email;
    public String password;
    public boolean isCompany;
    public String message;
}
