package com.license.studentscenespring.repository;

import com.license.studentscenespring.model.User;
import com.license.studentscenespring.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    List<User> findAllByEmail(String email);

}
