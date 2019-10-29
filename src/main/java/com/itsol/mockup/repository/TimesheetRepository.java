package com.itsol.mockup.repository;

import com.itsol.mockup.entity.TimeSheetEntity;
import com.itsol.mockup.entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimesheetRepository extends JpaRepository<TimeSheetEntity,Long> {

    TimeSheetEntity getTimeSheetEntityByTimesheetId(Long id);
    Page<TimeSheetEntity> findTimeSheetEntitiesByUser(UsersEntity usersEntity, Pageable pageable);
}
