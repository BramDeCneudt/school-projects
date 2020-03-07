/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.upload;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import domain.model.Photo;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Bram
 */
public class UploaderCDN implements Uploader {

    private String cloud_name, api_key, api_secret;
    Cloudinary cloudinary;

    public UploaderCDN(String cloud_name, String api_key, String api_secret) {
        this.cloud_name = cloud_name;
        this.api_key = api_key;
        this.api_secret = api_secret;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloud_name,
                "api_key", api_key,
                "api_secret", api_secret));
    }

    @Override
    public void upload(Photo photo, String beginPath, MultipartFile image) {
        if (image.isEmpty()) {
            throw new UploaderException("file is leeg");
        } else {
            try {
                Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
                HashMap<String, String> result = new HashMap<>(uploadResult);

                photo.setImagePath(result.get("url"));
            } catch (Exception e) {
                throw new UploaderException(e.getMessage());
            }
        }
    }

    @Override
    public void editUpload(Photo photo, String beginPath, MultipartFile image) {
        if (!image.isEmpty()) {
            try {
                cloudinary.uploader().destroy(getPublicId(photo.getImagePath()), ObjectUtils.emptyMap());
                this.upload(photo, beginPath, image);
            } catch (Exception e) {
                throw new UploaderException(e.getMessage());
            }
        }

    }

    private String getPublicId(String url) {
        String[] splittedUrl = url.split("/");
        String image = splittedUrl[splittedUrl.length - 1];
        return image.split(Pattern.quote("."))[0];
    }

    @Override
    public void deleteUpload(Photo photo, String beginPath) {
            try {
                cloudinary.uploader().destroy(getPublicId(photo.getImagePath()), ObjectUtils.emptyMap());
            } catch (Exception e) {
                throw new UploaderException(e.getMessage());
            }
        
    }

}
