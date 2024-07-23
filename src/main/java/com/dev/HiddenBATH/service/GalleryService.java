package com.dev.HiddenBATH.service;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.HiddenBATH.model.Gallery;
import com.dev.HiddenBATH.repository.GalleryRepository;

@Service
public class GalleryService {

	@Value("${spring.upload.path}")
	private String commonPath;

    @Autowired
    private GalleryRepository galleryRepository;

    public Gallery saveGallery(MultipartFile file, String type) throws IOException {
        
    	if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String contentType = file.getContentType();
        String fileExtension = "";

        if (contentType != null && contentType.contains("image/jpeg")) {
            fileExtension = ".jpg";
        } else if (contentType != null && contentType.contains("image/png")) {
            fileExtension = ".png";
        } else {
            throw new IllegalArgumentException("Unsupported file type");
        }

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        String newFileName = generatedString + fileExtension;
        String filePath = commonPath + "/gallery/" + newFileName;
        String fileRoad = "/upload/gallery/" + newFileName;

        // Save file to disk
        File dest = new File(filePath);
        file.transferTo(dest);

        // Create and save Gallery entity
        Gallery gallery = new Gallery();
        if (type.equals("gallery")) {
            gallery.setGalleryPath(filePath);
            gallery.setGalleryName(newFileName);
            gallery.setGalleryRoad(fileRoad);
        } else if (type.equals("thumb")) {
            gallery.setGalleryThumbPath(filePath);
            gallery.setGalleryThumbName(newFileName);
            gallery.setGalleryThumbRoad(fileRoad);
        }

        return galleryRepository.save(gallery);
    }
}
