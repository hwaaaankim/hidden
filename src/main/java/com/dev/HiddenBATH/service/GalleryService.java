package com.dev.HiddenBATH.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.HiddenBATH.model.Gallery;
import com.dev.HiddenBATH.repository.GalleryRepository;

@Service
public class GalleryService {

	@Autowired
	GalleryRepository galleryRepository;
	
	@Value("${spring.upload.path}")
	private String commonPath;
	
	public void insertGallery(MultipartFile thumb, MultipartFile gallery) throws IOException {

        // 실제 파일 저장 위치 (절대 경로)
        String path = commonPath + "/gallery/";
        // 파일 resource 로드 url
        String road = "/administration/upload/gallery/";

        // 디렉토리가 존재하지 않으면 생성
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 랜덤한 8자리 영문자로 파일명 생성
        String randomFilename = UUID.randomUUID().toString().substring(0, 8);

        // thumb 파일 처리
        String thumbExtension = getFileExtension(thumb.getOriginalFilename());
        String thumbFilename = "thumb_" + randomFilename + thumbExtension;
        File thumbFile = new File(path + thumbFilename);
        thumb.transferTo(thumbFile);

        // gallery 파일 처리
        String galleryExtension = getFileExtension(gallery.getOriginalFilename());
        String galleryFilename = randomFilename + galleryExtension;
        File galleryFile = new File(path + galleryFilename);
        gallery.transferTo(galleryFile);

        // Gallery 엔티티 생성 및 저장
        Gallery galleryEntity = new Gallery();
        galleryEntity.setPath(path + galleryFilename);
        galleryEntity.setName(galleryFilename);
        galleryEntity.setRoad(road + galleryFilename);
        galleryEntity.setThumbPath(path + thumbFilename);
        galleryEntity.setThumbRoad(road + thumbFilename);
        galleryEntity.setThumbName(thumbFilename);

        galleryRepository.save(galleryEntity);
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.'));
    }
    
    public List<List<Gallery>> getGalleriesInTwoHalves() {
        List<Gallery> allGalleries = galleryRepository.findAll();
        int totalGalleries = allGalleries.size();
        int halfSize = totalGalleries / 2;

        List<Gallery> firstHalf = new ArrayList<>(allGalleries.subList(0, halfSize));
        List<Gallery> secondHalf = new ArrayList<>(allGalleries.subList(halfSize, totalGalleries));

        List<List<Gallery>> splitGalleries = new ArrayList<>();
        splitGalleries.add(firstHalf);
        splitGalleries.add(secondHalf);

        return splitGalleries;
    }
}
