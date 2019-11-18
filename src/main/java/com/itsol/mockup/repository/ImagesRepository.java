package com.itsol.mockup.repository;

import com.itsol.mockup.entity.ImageEntity;
import com.itsol.mockup.web.dto.image.ImagesDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<ImageEntity,Long> {
    ImageEntity findImageEntityByImageId(Long id);
//    findImageEntityByImageId

}
