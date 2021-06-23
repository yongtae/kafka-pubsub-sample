package com.example.kafka;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.kafka.service.MessageService;

@Service
public class KafkaSampleConsumer {
	private static final Logger log = LoggerFactory.getLogger(KafkaSampleConsumer.class);
	
	@Autowired
	private MessageService messageService;
	
	@KafkaListener(topics = "kafka", groupId = "aten-group")
//	@KafkaListener(topics = "kafka")
	public void consume(String message) throws IOException {
//		System.out.println("receive message from kafka topic : " + message);
		log.info("Message received: " + message.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    Date resDate = new Date();
        String resMsg = message.toString().replace("응답시간", sdf.format(resDate));
        messageService.fileSave(resMsg,"redis_sub01.log"); //응답메세지 파일저장
	}

}
