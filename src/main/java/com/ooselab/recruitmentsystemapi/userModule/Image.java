package com.ooselab.recruitmentsystemapi.userModule;

import org.springframework.web.multipart.MultipartFile;

public class Image {

    public int id;

    public byte[] data;

    public int user_id;

}

class ImageRequest {
    public MultipartFile file;
    public int user_id;

}