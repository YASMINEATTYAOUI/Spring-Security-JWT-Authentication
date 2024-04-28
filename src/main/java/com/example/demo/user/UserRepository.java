package com.example.demo.user;

import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Long>{

    public User findByUsername(String username);
    //Boolean existsByUserName(String email);
    /*
    public default User findUserByEmail(String email){
        User user = new User(email,"123456");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        return user;
    }

     */
    //boolean existsByEmail(String email);
}
