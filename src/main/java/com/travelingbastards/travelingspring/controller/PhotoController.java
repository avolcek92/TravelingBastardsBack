package com.travelingbastards.travelingspring.controller;

import com.travelingbastards.travelingspring.service.photo.PhotoService;
import com.travelingbastards.travelingspring.service.user.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/api/")
public class PhotoController {

    @Resource
    private UserService userService;

    @Resource
    private PhotoService photoService;


    @PostMapping("registration/photos/{nickName}")
    public String ProfilePhotoAdd(@PathVariable("nickName") String nickName, @RequestParam("image") MultipartFile photo) throws IOException {
        photoService.uploadPhoto(nickName, photo);
        return "photo uploaded";
    }

    @PostMapping("admin/photos/{nickName}") //need to change to multiple files
    public String galleryAdd(@PathVariable("nickName") String nickName, @RequestParam("image") MultipartFile photo) throws IOException {
        photoService.uploadPhoto(nickName, photo);
        return "photo uploaded";
    }




}
