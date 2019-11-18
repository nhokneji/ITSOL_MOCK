package com.itsol.mockup.web.rest.image;

import com.itsol.mockup.services.ImagesService;
import com.itsol.mockup.web.dto.response.ArrayResultDTO;
import com.itsol.mockup.web.dto.response.BaseResultDTO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@Scope("request")
@CrossOrigin
public class ImagesController {
    @Autowired
    private ImagesService imagesService;

    @RequestMapping(value = "/image/list", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> findAll() {
        BaseResultDTO result = imagesService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ResponseEntity<BaseResultDTO> searchImageById(@RequestParam("id") Long id) {
        BaseResultDTO result = imagesService.findImageById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //    headers = "Content-Type= multipart/form-data"
    @RequestMapping(value = "/image", headers = "Content-Type= multipart/form-data" ,method = RequestMethod.POST)
            public ResponseEntity<BaseResultDTO>addImage(@RequestPart("file") MultipartFile file, HttpServletRequest request)
            throws IOException {
        BaseResultDTO result = imagesService.addImage(file, request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/image", method = RequestMethod.DELETE)
    public ResponseEntity<BaseResultDTO> deleteImage(@RequestParam Long id) {
        BaseResultDTO result = imagesService.deleteImages(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
//    @RequestMapping(value = "/downloadFile/{fileName:.+}",method = RequestMethod.GET)
//    public ResponseEntity<Resource> downloadImage(@PathVariable String fileName, HttpServletRequest request){
//        Resource resource = imagesService.loadFileAsResource(fileName);
//
//        // Try to determine file's content type
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//           ex.getMessage();
//        }
//
//        // Fallback to the default content type if type could not be determined
//        if(contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }

}
