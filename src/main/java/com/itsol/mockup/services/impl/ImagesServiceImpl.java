package com.itsol.mockup.services.impl;

import com.itsol.mockup.entity.ImageEntity;
import com.itsol.mockup.repository.ImagesRepository;
import com.itsol.mockup.services.ImagesService;
import com.itsol.mockup.web.FileStorageException;
import com.itsol.mockup.web.FileStorageProperties;
import com.itsol.mockup.web.MyFileNotFoundException;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import com.itsol.mockup.web.dto.response.SingleResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImagesServiceImpl extends BaseService implements ImagesService {
    private final Path fileStorageLocation; //Đường dẫn lưu file

    private static String UPLOAD_DIR = "Upload";

    @Autowired
    ImagesRepository imagesRepository;

    @Autowired
    public ImagesServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize(); //get ra đường dẫn lưu file tuyệt đối trong file cấu hình property

        try {
            Files.createDirectories(this.fileStorageLocation); //Tạo đường dẫn thư mục
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public ArrayResultDTO<ImageEntity> findAll() {
        ArrayResultDTO<ImageEntity> arrayResultDTO = new ArrayResultDTO<>();
        arrayResultDTO.setSuccess(imagesRepository.findAll(), 1L, 2);
        return arrayResultDTO;
    }

    @Override
    public BaseResultDTO addImage(MultipartFile file, HttpServletRequest httpServletRequest) {
        logger.info("ADD IMAGES");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String host = "http://" + httpServletRequest.getHeader("host") + "/" + UPLOAD_DIR + "/";

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("api/downloadFile/")
//                    .path(fileName)
//                    .toUriString(); //ghép nối các phần để tạo thành link download file
            ImageEntity imageEntity = new ImageEntity(fileName, host + fileName);
            imageEntity = imagesRepository.save(imageEntity);
            singleResultDTO.setSuccess(imageEntity);
        } catch (Exception e) {
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage());
        }


        return singleResultDTO;
    }

    @Override
    public BaseResultDTO deleteImages(Long id) {
        logger.info("DELETE IMAGES");
        SingleResultDTO singleResultDTO = new SingleResultDTO();
        try {
            if (id != null) {
                imagesRepository.deleteById(id);
                singleResultDTO.setSuccess();
            }
            logger.info("DELETE IMAGES FROM:" + singleResultDTO.getErrorCode());
        } catch (Exception e) {
            singleResultDTO.setFail(e.getMessage());
            logger.error(e.getMessage());
        }

        return singleResultDTO;
    }

    @Override
    public BaseResultDTO findImageById(Long id) {
        SingleResultDTO<ImageEntity> singleResultDTO = new SingleResultDTO<>();
        try {
            ImageEntity imageEntity = imagesRepository.findImageEntitiesByImageId(id);
            if (imageEntity != null) {
                singleResultDTO.setSuccess(imageEntity);
            }
            logger.info("SEARCH IMAGE BY ID RESPONSE: " + singleResultDTO.getErrorCode());
        } catch (Exception e) {
            logger.error("findImageById err" + e.getMessage(), e);
            singleResultDTO.setFail(e.getMessage());
        }
        return singleResultDTO;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
