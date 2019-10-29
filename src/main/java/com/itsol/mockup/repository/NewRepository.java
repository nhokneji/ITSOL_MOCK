package com.itsol.mockup.repository;

import com.itsol.mockup.entity.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewRepository extends JpaRepository<NewEntity,Long> {
    NewEntity getNewEntityByNewId(Long id);
}
