package com.prepaWeb.Repositories.UserProfileRepository;

import com.prepaWeb.Repositories.UserRepository.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends CassandraRepository<UserProfile> {

    @Query("INSERT INTO users (student_id,name,firstname,email,password,school,year,actual_job,city,description,promotion) VALUES (?0, ?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)")
    void createUserProfile(String id, String name, String firstname, String mail, String password, String school, int year, String actual_job, String city, String description, int promotion);

    @Query("SELECT * FROM users")
    List<UserProfile> getUserProfileList();

    @Query("SELECT * FROM users WHERE student_id = ?0")
    UserProfile getUserProfile(String id);

    @Query("INSERT INTO users (student_id,name,firstname,email,school,year,actual_job,city,description,promotion) VALUES (?0, ?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)")
    void updateUserProfileWithoutPassword(String profileId, String name, String firstname, String email, String school, int year, String actual_job, String city, String country, int promotion);
}
