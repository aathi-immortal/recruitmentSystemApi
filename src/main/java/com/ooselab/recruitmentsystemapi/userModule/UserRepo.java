package com.ooselab.recruitmentsystemapi.userModule;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate template;

    public User Save(User user) {

        String sql = "insert into loginuser (username,email,password) values (?,?,?)";
        if (alreadyRegistered(user)) {
            user.message = "user already registred";
            return user;
        }
        int row = template.update(sql, user.userName, user.email, user.password);
        user.message = "success";
        return user;
    }

    public boolean alreadyRegistered(User user) {
        String sql = "SELECT COUNT(*) FROM loginuser WHERE email = ?";
        Integer count = template.queryForObject(sql, Integer.class, user.email);
        return count != null && count > 0;

    }

    public boolean userExists(User user) {
        String sql = "SELECT COUNT(*) FROM loginuser WHERE email = ? AND password = ?";
        Integer count = template.queryForObject(sql, Integer.class, user.email, user.password);
        return count != null && count > 0;
    }

    public boolean isAdmin(User user) {
        String sql = "SELECT COUNT(*) FROM loginuser WHERE email = ? AND password = ? AND iscompany";
        Integer count = template.queryForObject(sql, Integer.class, user.email, user.password);
        return count != null && count > 0;
    }

    public List<User> getAllUser() {
        String sql = "select * from loginuser";
        RowMapper<User> rowMapper = new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet result, int rows) throws SQLException {
                User user = new User();
                user.id = result.getInt("ID");
                user.userName = result.getString("USERNAME");
                user.email = result.getString("EMAIL");
                user.password = result.getString("PASSWORD");
                user.isCompany = result.getBoolean("ISCOMPANY");
                return user;
            }

        };

        return template.query(sql, rowMapper);
    }

    public int getUserId(User user) {
        String query = "SELECT id from loginuser where email = ?";
        Integer user_id = template.queryForObject(query, Integer.class, user.email);
        return user_id;
    }

    public List<Job> getCreatedJobs(int userId) {
        String sql = "SELECT * FROM job WHERE user_id = ?";
        RowMapper<Job> rowMapper = new RowMapper<Job>() {
            @Override
            public Job mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Job job = new Job();
                job.setJobId(resultSet.getInt("id"));
                job.setJobName(resultSet.getString("jobName"));
                job.setJobSalary(resultSet.getInt("jobSalary"));
                job.setYearOfSkill(resultSet.getInt("yearOfSkill"));
                job.setSkill(resultSet.getString("skill"));
                job.setNoticePeriod(resultSet.getString("noticePeriod"));
                job.setUserId(resultSet.getInt("user_id"));
                return job;
            }
        };
        return template.query(sql, rowMapper, userId);
    }

    public String addJob(Job job) {
        String query = "INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(query, job.jobName, job.jobSalary, job.yearOfSkill, job.skill, job.noticePeriod, job.userId);
        return "success";
    }

    public String removeJob(int jobId) {
        String query = "DELETE from job where id = ?";
        template.update(query, jobId);
        return "success";
    }
}
