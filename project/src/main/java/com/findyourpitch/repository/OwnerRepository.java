package com.findyourpitch.repository;

import com.findyourpitch.entities.Pitch;
import com.findyourpitch.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<User, Integer> {

    @Query(value = "select distinct users.user_id, users.first_name, \n" +
            "users.last_name, users.age, users.user_role, users.user_password, users.user_name\n" +
            "from users\n" +
            "inner join pitches \n" +
            "on users.user_id = (\n" +
            "\tselect distinct user_id \n" +
            "\tfrom pitches where user_id = ?1 )", nativeQuery = true)
    List<User> findOwnerByID(Integer ownerID);

    @Query(value = "select distinct users.user_id, users.first_name, \n" +
            "users.last_name, users.age, users.user_role, users.user_password, users.user_name\n" +
            "from users \n" +
            "inner join pitches \n" +
            "on users.user_id = pitches.user_id", nativeQuery = true)
    List<User> findOwners();

}
