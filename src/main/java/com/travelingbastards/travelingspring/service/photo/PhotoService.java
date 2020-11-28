package com.travelingbastards.travelingspring.service.photo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class PhotoService {

    @Value("${upload.path}")
    private String uploadPath;


    public void uploadPhoto(String nickName, MultipartFile file) throws IOException {
        if (file != null) {
            File uploadFolder = new File(uploadPath + "/" + nickName);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            file.transferTo(new File(uploadFolder + "/" + file.getOriginalFilename()));
        }
    }
    public void uploadPhotos(String nickName, MultipartFile files) throws IOException {
        if (files != null) {
            File uploadFolder = new File(uploadPath + "/" + nickName + "/"+ files.getOriginalFilename());
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }

            files.transferTo(new File(uploadFolder + "/" + files.getOriginalFilename()));
        }
    }
}
