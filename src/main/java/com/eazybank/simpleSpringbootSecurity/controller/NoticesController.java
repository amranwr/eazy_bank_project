package com.eazybank.simpleSpringbootSecurity.controller;

import java.util.List;

import com.eazybank.simpleSpringbootSecurity.models.Notice;
import com.eazybank.simpleSpringbootSecurity.repositories.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class NoticesController {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@GetMapping("/notices")
	public List<Notice> getNotices() {
		List<Notice> notices = (List<Notice>) noticeRepository.findAll();
		if (notices != null ) {
			return notices;
		}else {
			return null;
		}
	}

}
