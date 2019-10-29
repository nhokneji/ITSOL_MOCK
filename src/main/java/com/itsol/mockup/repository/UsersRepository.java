package com.itsol.mockup.repository;

import com.itsol.mockup.entity.UsersEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author anhvd_itsol
 */

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity getUsersEntitiesByUserId (Long id);
    Page<UsersEntity> getAllByUserNameAndFullName(String userName, String fullName, Pageable pageable);
    List<UsersEntity> findAll();
    UsersEntity getUsersByUserName(String username); //function for generate token
    UsersEntity findUsersEntityByUserName(String userName);
    UsersEntity findUsersEntityByEmail(String email);
}
