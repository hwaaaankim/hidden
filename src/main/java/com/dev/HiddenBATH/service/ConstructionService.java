package com.dev.HiddenBATH.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dev.HiddenBATH.model.Construction;
import com.dev.HiddenBATH.repository.ConstructionRepository;

@Service
public class ConstructionService {

	@Autowired
	ConstructionRepository constructionRepository;
	
	@Value("${spring.upload.path}")
	private String commonPath;
	
	 public void insertConstruction(MultipartFile thumb, MultipartFile gallery) throws IOException {

	        // 실제 파일 저장 위치 (절대 경로)
	        String path = commonPath + "/construction/";
	        // 파일 resource 로드 url
	        String road = "/administration/upload/construction/";

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

	        // construction 파일 처리
	        String constructionExtension = getFileExtension(gallery.getOriginalFilename());
	        String constructionFilename = randomFilename + constructionExtension;
	        File constructionFile = new File(path + constructionFilename);
	        gallery.transferTo(constructionFile);

	        // Construction 엔티티 생성 및 저장
	        Construction constructionEntity = new Construction();
	        constructionEntity.setPath(path + constructionFilename);
	        constructionEntity.setName(constructionFilename);
	        constructionEntity.setRoad(road + constructionFilename);
	        constructionEntity.setThumbPath(path + thumbFilename);
	        constructionEntity.setThumbRoad(road + thumbFilename);
	        constructionEntity.setThumbName(thumbFilename);

	        constructionRepository.save(constructionEntity);
	    }

	    private String getFileExtension(String filename) {
	        return filename.substring(filename.lastIndexOf('.'));
	    }
}
