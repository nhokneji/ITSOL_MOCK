package com.itsol.mockup.repository;

import com.itsol.mockup.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author anhvd_itsol
 */

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users getUsersById (Long id);
    Page<Users> getAllByUserNameAndFullName(String userName, String fullName, Pageable pageable);
}
