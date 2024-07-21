package com.dev.HiddenBATH.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zeroturnaround.zip.ZipUtil;

import com.dev.HiddenBATH.model.product.Product;
import com.dev.HiddenBATH.model.product.ProductFile;
import com.dev.HiddenBATH.model.product.ProductImage;
import com.dev.HiddenBATH.repository.product.ProductFileRepository;
import com.dev.HiddenBATH.repository.product.ProductImageRepository;
import com.dev.HiddenBATH.repository.product.ProductRepository;
import com.dev.HiddenBATH.utils.DirectoryRefactoringUtils;

@Service
public class ZipService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductImageRepository productImageRepository;
	
	@Autowired
	ProductFileRepository productFileRepository;
	
	@Autowired
	DirectoryRefactoringUtils directoryRefactoringUtils;
	
	@Value("${spring.upload.env}")
	private String env;

	@Value("${spring.upload.path}")
	private String commonPath;
	
	public void directoryRefactoring(MultipartFile file) throws IOException {
		directoryRefactoringUtils.handleZipUpload(file);
	}
	
	public void zipProductInsert(MultipartFile file) throws IOException {
        String productDirPath = commonPath + "/product";
        File productDir = new File(productDirPath);
        cleanAndCreateDirectory(productDir);

        File zipFile = new File(commonPath + "/product.zip");
        try (FileOutputStream fos = new FileOutputStream(zipFile)) {
            fos.write(file.getBytes());
        }

        ZipUtil.explode(zipFile);
        processProductFiles(productDir);
    }

    private void cleanAndCreateDirectory(File directory) throws IOException {
        if (directory.exists() && directory.isDirectory()) {
            FileUtils.cleanDirectory(directory);
        } else {
            directory.mkdirs();
        }
    }

    private void processProductFiles(File productDir) {
        for (File product : productDir.listFiles()) {
            if (product.isDirectory() && !product.getName().equals("product")) {
                String productCode = product.getName();
                Optional<Product> productOpt = productRepository.findByProductCode(productCode);

                if (productOpt.isPresent()) {
                    Product productEntity = productOpt.get();
                    for (File sort : product.listFiles()) {
                        if (sort.isDirectory()) {
                            processSortedFiles(sort, productEntity, productCode);
                        }
                    }
                }
            }
        }
    }

    private void processSortedFiles(File sort, Product productEntity, String productCode) {
        switch (sort.getName()) {
            case "slide":
                saveProductImages(sort, productEntity, productCode);
                break;
            case "rep":
                saveRepImage(sort, productEntity, productCode);
                break;
            case "files":
                saveProductFiles(sort, productEntity, productCode);
                break;
        }
    }

    private void saveProductImages(File sort, Product productEntity, String productCode) {
        for (File file : sort.listFiles()) {
            String fileName = file.getName();
            ProductImage image = new ProductImage();
            image.setProductId(productEntity.getId());
            image.setProductImagePath(commonPath + "/product/" + productCode + "/slide/" + fileName);
            image.setProductImageRoad("/administration/upload/product/" + productCode + "/slide/" + fileName);
            image.setProductImageName(fileName);
            productImageRepository.save(image);
        }
    }

    private void saveRepImage(File sort, Product productEntity, String productCode) {
        for (File file : sort.listFiles()) {
            String fileName = file.getName();
            productEntity.setProductRepImageName(fileName);
            productEntity.setProductRepImageExtension(productCode);
            productEntity.setProductRepImageOriginalName(productCode);
            productEntity.setProductRepImagePath(commonPath + "/product/" + productCode + "/rep/" + fileName);
            productEntity.setProductRepImageRoad("/administration/upload/product/" + productCode + "/rep/" + fileName);
            productRepository.save(productEntity);
        }
    }

    private void saveProductFiles(File sort, Product productEntity, String productCode) {
        for (File file : sort.listFiles()) {
            String fileName = file.getName();
            ProductFile productFile = new ProductFile();
            productFile.setProductId(productEntity.getId());
            productFile.setProductFilePath(commonPath + "/product/" + productCode + "/files/" + fileName);
            productFile.setProductFileRoad("/administration/upload/product/" + productCode + "/files/" + fileName);
            productFile.setProductFileName(fileName);
            productFile.setProductFileDate(new Date());
            productFileRepository.save(productFile);
        }
    }

    public File createZip(String env) throws IOException {
        String absolutePath = new File("").getAbsolutePath() + "/";
        String existFilePath = "";
        String zipFilePath = "";
        if(env.equals("local")) {
            existFilePath = absolutePath + commonPath + "/company";
            zipFilePath = absolutePath + "company.zip";
        } else if(env.equals("prod")) {
            existFilePath = commonPath + "/company";
            zipFilePath = commonPath + "company.zip";
        }
        ZipUtil.pack(new File(existFilePath), new File(zipFilePath));
        return new File(zipFilePath);
    }
}
