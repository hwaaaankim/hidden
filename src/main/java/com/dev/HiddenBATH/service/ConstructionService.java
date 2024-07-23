package com.dev.HiddenBATH.service;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.HiddenBATH.model.Construction;
import com.dev.HiddenBATH.repository.ConstructionRepository;

@Service
public class ConstructionService {

	@Value("${commonPath}")
    private String commonPath;

    @Autowired
    private ConstructionRepository constructionRepository;

    public Construction saveConstruction(MultipartFile file, String type) throws IOException {
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
        String filePath = commonPath + "/construction/" + newFileName;
        String fileRoad = "/upload/construction/" + newFileName;

        // Save file to disk
        File dest = new File(filePath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        file.transferTo(dest);

        // Create and save Construction entity
        Construction construction = new Construction();
        if (type.equals("construction")) {
            construction.setConstructionPath(filePath);
            construction.setConstructionName(newFileName);
            construction.setConstructionRoad(fileRoad);
        } else if (type.equals("thumb")) {
            construction.setConstructionThumbPath(filePath);
            construction.setConstructionThumbName(newFileName);
            construction.setConstructionThumbRoad(fileRoad);
        }

        return constructionRepository.save(construction);
    }
}
