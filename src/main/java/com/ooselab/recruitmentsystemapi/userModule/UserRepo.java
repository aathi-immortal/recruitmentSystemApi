package com.ooselab.recruitmentsystemapi.userModule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

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

    public Message addJob(Job job) {
        String query = "INSERT INTO job (jobName, jobSalary, yearOfSkill, skill, noticePeriod, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(query, job.jobName, job.jobSalary, job.yearOfSkill, job.skill, job.noticePeriod, job.userId);
        return new Message();
    }

    public Message removeJob(int jobId) {
        String query = "DELETE from job where id = ?";
        template.update(query, jobId);
        return new Message();
    }

    public List<Job> getAllJobs(int user_id) {

        String query = " SELECT * FROM job j WHERE j.id NOT IN ( SELECT job_id FROM user_job uj WHERE uj.user_id = ?  ); ";
        // String query = "select * from job";

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

        return template.query(query, rowMapper, user_id);
    }

    public Message applyJob(UserJob job) {

        String query = "Insert into user_job (user_id,job_id) values(?,?)";
        System.out.println(job.user_id);
        System.out.println(job.job_id);
        System.out.println(job.id);
        template.update(query, job.user_id, job.job_id);
        Message message = new Message();
        return message;
    }

    public List<Job> getAppliedJob(int user_id) {
        String query = "SELECT job.* FROM job INNER JOIN user_job ON job.id = user_job.job_id WHERE     user_job.user_id = ?;";
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
        return template.query(query, rowMapper, user_id);

    }

    public List<AbstartUser> getRegUsers(int jobId) {
        String query = "SELECT * from loginuser inner join user_job on user_job.job_id = ? and loginuser.id = user_job.user_id ";
        RowMapper<AbstartUser> rowMapper = new RowMapper<AbstartUser>() {
            @Override
            public AbstartUser mapRow(ResultSet result, int rows) throws SQLException {
                AbstartUser user = new AbstartUser();
                user.id = result.getInt("ID");
                user.userName = result.getString("USERNAME");
                user.email = result.getString("EMAIL");

                return user;
            }

        };
        return template.query(query, rowMapper, jobId);
    }

    public String uploadResume(ImageRequest file) {
        String message;
        if (getResume(file.user_id).length != 0)

        {
            try {
                deleteResume(file.user_id);
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
        try {
            String sql = "INSERT INTO resume (file_data, user_id) VALUES (?, ?)";
            PreparedStatement statement = template.getDataSource().getConnection().prepareStatement(sql);
            statement.setBytes(1, file.file.getBytes());
            statement.setInt(2, file.user_id);
            statement.executeUpdate();
            message = "Resume uploaded successfully";
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed to upload resume";
        }
        return message;
    }

    public void deleteResume(int userId) throws SQLException {
        String sql = "DELETE FROM resume WHERE user_id = ?";
        PreparedStatement statement = template.getDataSource().getConnection().prepareStatement(sql);
        statement.setInt(1, userId);
        statement.executeUpdate();
    }

    public byte[] getResume(int userId) {

        String sql = "SELECT file_data FROM resume WHERE user_id = ?";
        try {
            return template.queryForObject(sql, byte[].class, userId);
        } catch (EmptyResultDataAccessException e) {
            // No resume found for the user ID, return an empty byte array
            return new byte[0];
        }

    }
}
