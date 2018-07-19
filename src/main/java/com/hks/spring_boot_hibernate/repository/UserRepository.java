package com.hks.spring_boot_hibernate.repository;

import com.hks.spring_boot_hibernate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//JpaRepository
public interface UserRepository extends JpaRepository<User,String> {
    //自定义查询字段
    User findByUsername(String name);

    //原生sql查询
    @Query(value = "select * from user u where u.username = :username ", nativeQuery = true)
    User findUsername(@Param("username") String username);

}
