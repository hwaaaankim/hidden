package com.dev.HiddenBATH.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev.HiddenBATH.config.KakaoConfig;
import com.dev.HiddenBATH.dto.AgencyCreateRequest;
import com.dev.HiddenBATH.dto.AgencyResponse;
import com.dev.HiddenBATH.dto.AgencyUpdateRequest;
import com.dev.HiddenBATH.service.AgencyService;
import com.dev.HiddenBATH.service.ConstructionService;
import com.dev.HiddenBATH.service.GalleryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 관리자 컨트롤러
 * - 화면 반환: 뷰 이름 반환
 * - 데이터 API: @ResponseBody 로 JSON 반환 (REST 컨트롤러 대체)
 */
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final GalleryService galleryService;
    private final AgencyService agencyService;
    private final ConstructionService constructionService;
    private final KakaoConfig kakaoConfig;
    private final ObjectMapper objectMapper;
    
    /* =========================
     *       화면(뷰) 라우팅
     * ========================= */

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
    public String agencyManager(Model model) {
        model.addAttribute("kakaoJavascriptKey", kakaoConfig.getJavascriptKey());
        return "administration/client/agencyManager";
    }

    @GetMapping("/agencyInsertForm")
    public String agencyInsertForm(Model model) {
        model.addAttribute("kakaoJavascriptKey", kakaoConfig.getJavascriptKey());
        return "administration/client/agencyInsertForm";
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
            MultipartFile thumb,
            MultipartFile gallery
    ) throws Exception {
        galleryService.insertGallery(thumb, gallery);
        StringBuffer sb = new StringBuffer();
        String msg = "갤러리 이미지가 등록 되었습니다.";

        sb.append("alert('" + msg + "');");
        sb.append("location.href='/admin/galleryInsertForm'");
        sb.append("</script>");
        sb.insert(0, "<script>");

        return sb.toString();
    }

    @GetMapping("/exampleManager")
    public String exampleManager() {
        return "administration/site/exampleManager";
    }

    @GetMapping("/exampleInsertForm")
    public String exampleInsertForm() {
        return "administration/site/exampleInsertForm";
    }

    @PostMapping("/exampleInsert")
    @ResponseBody
    public String exampleInsert(
            MultipartFile thumb,
            MultipartFile construction
    ) throws Exception {
        constructionService.insertConstruction(thumb, construction);
        StringBuffer sb = new StringBuffer();
        String msg = "시공사례 이미지가 등록 되었습니다.";

        sb.append("alert('" + msg + "');");
        sb.append("location.href='/admin/exampleInsertForm'");
        sb.append("</script>");
        sb.insert(0, "<script>");

        return sb.toString();
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

    /* =========================
     *      대리점 관리 API
     *  (ResponseBody = JSON)
     * ========================= */

    /**
     * 대리점 목록 검색
     *  - type: name | contact (기본 name)
     *  - keyword: 부분일치 (contain)
     *  - page: 0-base
     *  - size: 10/30/50/100 등
     */
    @GetMapping(value = "/api/agencies", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Page<AgencyResponse>> listAgencies(
            @RequestParam(defaultValue = "name") String type,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<AgencyResponse> result = agencyService.search(type, keyword, pageable);
        return ResponseEntity.ok(result);
    }

    /** 대리점 단건 조회 */
    @GetMapping(value = "/api/agencies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<AgencyResponse> getAgency(@PathVariable Long id) {
        return ResponseEntity.ok(agencyService.get(id));
    }

    /**
     * 대리점 등록
     * - multipart/form-data
     *   - part name="form" : AgencyCreateRequest (JSON)
     *   - part name="icon" : MultipartFile (선택)
     */
    @PostMapping(
        value = "/api/agencies",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AgencyResponse> createAgency(
            @RequestPart("form") @Valid AgencyCreateRequest form,
            @RequestPart(value = "icon", required = false) MultipartFile icon
    ) throws Exception {

        // ★ 수신 값 로깅 (문자열/좌표/연락처)
        log.info("[AgencyCreate] name='{}', sido='{}', sigungu='{}', bname='{}', postcode='{}'",
                form.getName(), form.getSido(), form.getSigungu(), form.getBname(), form.getPostcode());
        log.info("[AgencyCreate] road='{}', jibun='{}', detail='{}'",
                form.getRoadAddress(), form.getJibunAddress(), form.getAddressDetail());
        log.info("[AgencyCreate] lat={}, lng={}", form.getLatitude(), form.getLongitude());
        log.info("[AgencyCreate] tel='{}', mobile='{}', kakao='{}', staff='{}'",
                form.getTel(), form.getMobile(), form.getKakaoTalkLink(), form.getStaffName());
        if (icon != null) {
            log.info("[AgencyCreate] icon: name='{}', size={}, contentType={}",
                    icon.getOriginalFilename(), icon.getSize(), icon.getContentType());
        } else {
            log.info("[AgencyCreate] icon: null");
        }

        //(JSON 전체 원하면)
        log.debug("[AgencyCreate] JSON payload: {}", objectMapper.writeValueAsString(form));

        AgencyResponse res = agencyService.create(form, icon);
        return ResponseEntity.ok(res);
    }

    /**
     * 대리점 수정
     * - multipart/form-data
     *   - part name="form" : AgencyUpdateRequest (JSON)
     *   - part name="icon" : MultipartFile (선택, 교체 시 기존 파일 삭제)
     */
    @PutMapping(value = "/api/agencies/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<AgencyResponse> updateAgency(
            @PathVariable Long id,
            @RequestPart("form") AgencyUpdateRequest form,
            @RequestPart(value = "icon", required = false) MultipartFile icon
    ) throws Exception {
        return ResponseEntity.ok(agencyService.update(id, form, icon));
    }

    /** 대리점 삭제 (아이콘 파일도 함께 정리) */
    @DeleteMapping(value = "/api/agencies/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteAgency(@PathVariable Long id) {
        agencyService.delete(id);
        return ResponseEntity.ok().build();
    }
}
