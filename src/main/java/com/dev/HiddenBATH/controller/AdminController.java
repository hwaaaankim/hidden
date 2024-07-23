package com.dev.HiddenBATH.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev.HiddenBATH.model.Construction;
import com.dev.HiddenBATH.model.Gallery;
import com.dev.HiddenBATH.repository.ConstructionRepository;
import com.dev.HiddenBATH.repository.GalleryRepository;
import com.dev.HiddenBATH.service.ConstructionService;
import com.dev.HiddenBATH.service.GalleryService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	GalleryRepository galleryRepository;
	
	@Autowired
    private GalleryService galleryService;
	
	@Autowired
	ConstructionRepository constructionRepository;
	
	@Autowired
	ConstructionService constructionService;
	
	@Value("${spring.upload.env}")
	private String env;

	@Value("${spring.upload.path}")
	private String commonPath;
	
	@GetMapping({"/","", "/inquiryManager"})
	public String adminIndex(){
	
		return "administration/index";
	}
	
	@GetMapping("/inquiryDetail")
	public String inquiryDetail() {
		
		return "administration/client/inquiryDetail";
	}
	
	@GetMapping("/orderManager")
	public String orderManeger() {
		
		return "administration/client/orderManager";
	}
	
	@GetMapping("/orderDetail")
	public String orderDetail() {
		
		return "administration/client/orderDetail";
	}
	
	@GetMapping("/agencyManager")
	public String agencyManager(){
	
		return "administration/client/agencyManager";
	}
	
	@GetMapping("/agencyInsertForm")
	public String agencyInsertForm(){
	
		return "administration/client/agencyInsertForm";
	}
	
	@GetMapping("/agencyDetail")
	public String agencyDetail(){
	
		return "administration/client/agencyDetail";
	}
	
	@GetMapping("/noticeManager")
	public String noticeManager() {
		
		return "administration/site/noticeManager";
	}
	
	@GetMapping("/noticeInsertForm")
	public String noticeInsertForm() {
		
		return "administration/site/noticeInsertForm";
	}
	
	@GetMapping("/noticeCategoryManager")
	public String noticeCategoryManager() {
		
		return "administration/site/noticeCategoryManager";
	}
	
	@GetMapping("/instagramManager")
	public String instagramManager() {
		
		return "administration/site/instagramManager";
	}
	
	@GetMapping("/instagramInsertForm")
	public String instagramInsertForm() {
		
		return "administration/site/instagramInsertForm";
	}
	
	@GetMapping("/galleryManager")
	public String galleryManager() {
		
		return "administration/site/galleryManager";
	}
	
	@GetMapping("/galleryInsertForm")
	public String galleryInsertForm() {
		
		return "administration/site/galleryInsertForm";
	}
	
    @PostMapping("/galleryInsert")
    @ResponseBody
    public String galleryInsert(
            @RequestParam("gallery") MultipartFile gallery,
            @RequestParam("galleryThumb") MultipartFile galleryThumb) {

        try {
            // Save gallery image
            Gallery galleryEntity = galleryService.saveGallery(gallery, "gallery");

            // Save gallery thumbnail image
            Gallery thumbEntity = galleryService.saveGallery(galleryThumb, "thumb");

            // Set the thumb properties in the gallery entity
            galleryEntity.setGalleryThumbPath(thumbEntity.getGalleryPath());
            galleryEntity.setGalleryThumbName(thumbEntity.getGalleryName());
            galleryEntity.setGalleryThumbRoad(thumbEntity.getGalleryRoad());

            // Update the gallery entity with thumb info
            galleryService.saveGallery(gallery, "gallery");

            StringBuffer sb = new StringBuffer();
            String msg = "갤러리 이미지가 등록 되었습니다.";

            sb.append("alert('").append(msg).append("');");
            sb.append("location.href='/admin/galleryManager';");
            sb.append("</script>");
            sb.insert(0, "<script>");

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            StringBuffer sb = new StringBuffer();
            String msg = "파일 업로드 중 오류가 발생했습니다.";

            sb.append("alert('").append(msg).append("');");
            sb.append("location.href='/admin/galleryManager';");
            sb.append("</script>");
            sb.insert(0, "<script>");

            return sb.toString();
        } catch (IllegalArgumentException e) {
            StringBuffer sb = new StringBuffer();
            String msg = e.getMessage();

            sb.append("alert('").append(msg).append("');");
            sb.append("location.href='/admin/galleryManager';");
            sb.append("</script>");
            sb.insert(0, "<script>");

            return sb.toString();
        }
    }

	
	@GetMapping("/constructionManager")
	public String constructionManager() {
		
		return "administration/site/constructionManager";
	}
	
	@GetMapping("/constructionInsertForm")
	public String constructionInsertForm() {
		
		return "administration/site/constructionInsertForm";
	}
	
	@PostMapping("/constructionInsert")
	@ResponseBody
    public String constructionInsert(
            @RequestParam("construction") MultipartFile construction,
            @RequestParam("constructionThumb") MultipartFile constructionThumb) {

        try {
            // Save construction image
            Construction constructionEntity = constructionService.saveConstruction(construction, "construction");

            // Save construction thumbnail image
            Construction thumbEntity = constructionService.saveConstruction(constructionThumb, "thumb");

            // Set the thumb properties in the construction entity
            constructionEntity.setConstructionThumbPath(thumbEntity.getConstructionThumbPath());
            constructionEntity.setConstructionThumbName(thumbEntity.getConstructionThumbName());
            constructionEntity.setConstructionThumbRoad(thumbEntity.getConstructionThumbRoad());

            // Update the construction entity with thumb info
            constructionService.saveConstruction(construction, "construction");

            StringBuffer sb = new StringBuffer();
            String msg = "갤러리 이미지가 등록 되었습니다.";

            sb.append("alert('").append(msg).append("');");
            sb.append("location.href='/admin/constructionManager';");
            sb.append("</script>");
            sb.insert(0, "<script>");

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            StringBuffer sb = new StringBuffer();
            String msg = "파일 업로드 중 오류가 발생했습니다.";

            sb.append("alert('").append(msg).append("');");
            sb.append("location.href='/admin/constructionManager';");
            sb.append("</script>");
            sb.insert(0, "<script>");

            return sb.toString();
        } catch (IllegalArgumentException e) {
            StringBuffer sb = new StringBuffer();
            String msg = e.getMessage();

            sb.append("alert('").append(msg).append("');");
            sb.append("location.href='/admin/constructionManager';");
            sb.append("</script>");
            sb.insert(0, "<script>");

            return sb.toString();
        }
    }

	@GetMapping("/emailSendManager")
	public String emailSendManager() {
		
		return "administration/site/emailSendManager";
	}
	
	@GetMapping("/emailReceiveManager")
	public String emailReceiveManager() {
		
		return "administration/site/emailReceiveManager";
	}
	
	@GetMapping("/smsSendManager")
	public String smsSendManager() {
		
		return "administration/site/smsSendManager";
	}
	
	@GetMapping("/smsReceiveManager")
	public String smsReceiveManager() {
		
		return "administration/site/smsReceiveManager";
	}
	
	@GetMapping("/siteAccessManager")
	public String siteAccessManager() {
		
		return "administration/analytics/accessManager";
	}
	
	@GetMapping("/siteAccessDetail")
	public String siteAccessDetail() {
		
		return "administration/analytics/siteAccessDetail";
	}
	
	@GetMapping("/siteAnalytics")
	public String siteAnalytics() {
		
		return "administration/analytics/siteAnalytics";
	}
}
