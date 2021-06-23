package com.example.kafka;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.service.MessageService;
import com.example.kafka.service.PubRunnable;

@RestController
@RequestMapping("/api")
public class TestController {
	
	private static final Logger log = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private MessageService messageService;
	
	
	@RequestMapping(value = "/logFileInit.json", method = RequestMethod.GET)
	public ResponseEntity<HashMap> logFileInit(@RequestBody HashMap apiDto) throws IOException {
		messageService.fileListCreate(); //로그파일 초기화
		return new ResponseEntity<>(apiDto, HttpStatus.OK);
	}
	
	// 전체 파일라인수 확인
	@RequestMapping(value = "/fileLineCnt.json", method = RequestMethod.GET)
	public ResponseEntity<HashMap> fileLineCnt (@RequestBody HashMap apiDto) throws IOException {
		int pubTotalCnt = 0;
		int cnt = messageService.fileLineCnt("redis_sub01.log");
		apiDto.put("redis_sub01.log-cnt", cnt);
		
		for (int i=1; i<21; i++) {
			cnt = messageService.fileLineCnt("redis_pub"+String.format("%02d", i)+".log");
			apiDto.put("redis_pub"+String.format("%02d", i)+".log-cnt", cnt);
			pubTotalCnt += cnt;
		}
		apiDto.put("send-total-cnt", pubTotalCnt);
		
		log.info("--- fileLineCnt.json api 실행");
		return new ResponseEntity<>(apiDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pubSend.json", method = RequestMethod.GET)
	public ResponseEntity<HashMap> tc01_pubSend(@RequestBody HashMap apiDto) throws IOException {
		Thread thr = new Thread(new PubRunnable("pub"+String.format("%02d", 1),"sub"+String.format("%02d", 1),messageService));
		thr.start();
		log.info("--- pubSend.json api 실행");
		return new ResponseEntity<>(apiDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pubSend02.json", method = RequestMethod.GET)
	public ResponseEntity<HashMap> tc02_pubSend(@RequestBody HashMap apiDto) throws IOException {
		
		for (int i=1; i<6; i++) {
			Thread thr = new Thread(new PubRunnable("pub"+String.format("%02d", i),"sub"+String.format("%02d", 1),messageService));
			thr.start();
		}
		
		
		log.info("--- pubSend02.json api 실행");
		return new ResponseEntity<>(apiDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pubSend03.json", method = RequestMethod.GET)
	public ResponseEntity<HashMap> tc03_pubSend(@RequestBody HashMap apiDto) throws IOException {
		
		for (int i=1; i<11; i++) {
			Thread thr = new Thread(new PubRunnable("pub"+String.format("%02d", i),"sub"+String.format("%02d", 1),messageService));
			thr.start();
		}
		
		
		log.info("--- pubSend03.json api 실행");
		return new ResponseEntity<>(apiDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pubSend04.json", method = RequestMethod.GET)
	public ResponseEntity<HashMap> tc04_pubSend(@RequestBody HashMap apiDto) throws IOException {
		
		for (int i=1; i<16; i++) {
			Thread thr = new Thread(new PubRunnable("pub"+String.format("%02d", i),"sub"+String.format("%02d", 1),messageService));
			thr.start();
		}
		
		
		log.info("--- pubSend04.json api 실행");
		return new ResponseEntity<>(apiDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pubSend05.json", method = RequestMethod.GET)
	public ResponseEntity<HashMap> tc05_pubSend(@RequestBody HashMap apiDto) throws IOException {
		
		for (int i=1; i<21; i++) {
			Thread thr = new Thread(new PubRunnable("pub"+String.format("%02d", i),"sub"+String.format("%02d", 1),messageService));
			thr.start();
		}
		
		
		log.info("--- pubSend05.json api 실행");
		return new ResponseEntity<>(apiDto, HttpStatus.OK);
	}
	
}
