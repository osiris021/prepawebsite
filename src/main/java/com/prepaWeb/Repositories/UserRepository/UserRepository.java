package com.prepaWeb.Repositories.UserRepository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CassandraRepository<User> {

    List<User> findByEmail(@Param("email") String email);

    @Query("INSERT INTO users_ihm (email,password,roles) VALUES (?0, ?1, \'ROLE_STUDENT\')")
    void createUser(String mail, String password);
}
