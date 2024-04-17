package com.dev.HiddenBATH.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dev.HiddenBATH.repository.TestRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerConfiguration {

	@Autowired
	TestRepository testRepository;
	
	@Async
    @Scheduled(fixedDelay = 1000)
    public void test01() {
		int sample = testRepository.findById(1l).get().getSample();
		if(sample == 3) {
			log.info("1");
		}
        
    }
	
	@Async
    @Scheduled(fixedDelay = 1000)
    public void test02() {
		int sample = testRepository.findById(1l).get().getSample();
		if(sample == 3) {
			log.info("2");
		}
    }
}
