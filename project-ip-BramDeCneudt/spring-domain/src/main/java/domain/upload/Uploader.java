/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.upload;

import domain.model.Photo;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Bram
 */
public interface Uploader {
    
    void upload(Photo photo, String beginPath, MultipartFile image);
    void editUpload(Photo photo, String beginPath, MultipartFile image);
    void deleteUpload(Photo photo, String beginPath);
    
}
