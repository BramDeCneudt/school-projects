/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.upload;

import domain.model.Photo;
import java.io.File;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Bram
 */
public class UploaderLocal implements Uploader {

    @Override
    public void upload(Photo photo, String beginPath, MultipartFile image) {

        File file = new File(beginPath);
        if (!file.exists()) {
            file.mkdir();
        }

        String publicId = UUID.randomUUID().toString();

        String imageName = publicId + ".jpg";

        String filePath = beginPath + "/" + imageName;

        if (image.isEmpty()) {
            throw new UploaderException("file is leeg");
        } else {
            try {
                File imageFile = new File(filePath);
                image.transferTo(imageFile);
                photo.setImagePath(imageName);

            } catch (Exception e) {
                throw new UploaderException("er is fout gegaan bij het uploaden van het bestand: " + e.getMessage());
            }

        }

    }

    @Override
    public void editUpload(Photo photo, String beginPath, MultipartFile image) {
        if (!image.isEmpty()) {
            try {

                String imagePath = photo.getImagePath();
                String filePath = beginPath + "/" + imagePath;
                File imageFile = new File(filePath);

                image.transferTo(imageFile);

            } catch (Exception e) {
                throw new UploaderException("fout in editupload: " + e.getMessage());
            }

        }
    }

    @Override
    public void deleteUpload(Photo photo, String beginPath) {
        try {
            String imagePath = photo.getImagePath();
            String filePath = beginPath + "/" + imagePath;
            File imageFile = new File(filePath);
            imageFile.delete();
        } catch (Exception e) {
            throw new UploaderException("fout in deleteupload:" + e.getMessage());
        }

    }

}
