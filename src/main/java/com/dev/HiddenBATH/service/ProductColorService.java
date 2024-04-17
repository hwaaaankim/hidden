package com.dev.HiddenBATH.service;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dev.HiddenBATH.model.product.ProductColor;
import com.dev.HiddenBATH.repository.ProductColorRepository;

@Service
public class ProductColorService {

	@Autowired
	ProductColorRepository productColorRepository;
	
	@Value("${spring.upload.env}")
	private String env;
	
	@Value("${spring.upload.path}")
	private String commonPath;
	
	public String insertProductColor(
			String colorName,
			MultipartFile file
			) throws IOException {
        String absolutePath = new File("").getAbsolutePath() + "\\";
      
        int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		
		Random random = new Random();
		String generatedString = random.ints(leftLimit,rightLimit + 1)
				  .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				  .limit(targetStringLength)
				  .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				  .toString();
        
        String path = commonPath + "/color/";
        String road = "/administration/upload/color/";
        
        File fileFolder = new File(path);
        if(!fileFolder.exists()) {
        	fileFolder.mkdirs();
        }
        
        String contentType = file.getContentType();
        String originalFileExtension = "";
        
        if (ObjectUtils.isEmpty(contentType)){
            return "NONE";
        }else{
            if(contentType.contains("image/jpeg")){
                originalFileExtension = ".jpg";
            }
            else if(contentType.contains("image/png")){
                originalFileExtension = ".png";
            }
            else if(contentType.contains("image/gif")){
                originalFileExtension = ".gif";
            }
            else if(contentType.contains("application/pdf")) {
            	originalFileExtension = ".pdf";
            }
            else if(contentType.contains("application/x-zip-compressed")) {
            	originalFileExtension = ".zip";
            }
            else if(contentType.contains("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            	originalFileExtension = ".xlsx";
            }
            else if(contentType.contains("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
            	originalFileExtension = ".docx";
            }
            else if(contentType.contains("text/plain")) {
            	originalFileExtension = ".txt";
            }
            else if(contentType.contains("image/x-icon")) {
            	originalFileExtension = ".ico";
            }
            else if(contentType.contains("application/haansofthwp")) {
            	originalFileExtension = ".hwp";
            }
        }
        String fileName = generatedString + "." + originalFileExtension;
        ProductColor c = new ProductColor();
        if(env.equals("local")) {
        	fileFolder = new File(absolutePath + path + fileName);
        	c.setProductColorPath(absolutePath + path + fileName);
		}else if(env.equals("prod")) {
			fileFolder = new File(path + fileName);
			c.setProductColorPath(path + fileName);
		}
        file.transferTo(fileFolder);
        c.setProductColorRoad(road + fileName);
        c.setProductColorSubject(colorName);
        productColorRepository.save(c);
        
        return "success";
	}
	
	public void deleteProductColor(Long[] id) {
		for(Long i : id) {
			File colorFile = new File(productColorRepository.findById(i).get().getProductColorPath());
			colorFile.delete();
			productColorRepository.deleteById(i);
		}
	}
	
}

















