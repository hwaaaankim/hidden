package com.dev.HiddenBATH.service;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.dev.HiddenBATH.dto.OnlineAgencyResponse;

public interface OnlineAgencyService {

	OnlineAgencyResponse create(String name, String contact, String fax, String homepageUrl, MultipartFile logoFile);

	Page<OnlineAgencyResponse> search(String type, String keyword, int page, int size);

	OnlineAgencyResponse update(Long id, String name, String contact, String fax, String homepageUrl,
			MultipartFile newLogoFile, boolean removeLogo);

	void delete(Long id);
}
