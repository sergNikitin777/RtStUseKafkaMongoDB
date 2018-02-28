/**
 * 
 */
package com.kafkaiot.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kafkaiot.dao.DataStore;
import com.kafkaiot.dao.MongoDataStore;
import com.kafkaiot.service.EventProducer;
import com.kafkaiot.service.KafkaEventProducer;


/**
 * @author kafkaiot
 *
 */
@Controller
public class BaseController {

	private static final String KAFKA_PORT = "2181";
	private static final String KAFKA_HOST = "127.0.0.1";
	private static final String MONGO_HOST = "localhost";
	private static final int MONGO_PORT = 27017;
	private static final String VIEW_INDEX = "index";
	private static final String VIEW_DATA = "allData";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);
	private final static EventProducer eventProducer = KafkaEventProducer.getConnection(KAFKA_HOST, KAFKA_PORT);


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model) {
		return VIEW_INDEX;
	}

	/** 
	 * Events sent to kafka Cluster by the producer
     * @return
	 */
	
	@RequestMapping(value = "/send", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String sendEvent(@RequestBody Map<String, Object> map) {
		/*
		String jsonData;
		try {
			ObjectWriter ow = new ObjectMapper().writer();
			jsonData = ow.writeValueAsString(event);
			System.out.println("JsonData:-->"+jsonData);
		} catch (IOException e) {
			logger.error("Invalid request:" + e.getStackTrace());
			return "Error";
		}
		*/

		ObjectMapper mapperObj = new ObjectMapper();

		String jsonData = null;

		try {
			jsonData = mapperObj.writeValueAsString(map);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("JsonData:-->"+jsonData);

		eventProducer.send("test-events", jsonData);
		return "success";
	}
	
	@RequestMapping(value = "test-events1/send", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String sendEvent1(@RequestBody String event) {
		String jsonData;
		try {
			ObjectWriter ow = new ObjectMapper().writer();
			jsonData = ow.writeValueAsString(event);
			System.out.println("JsonData:-->"+jsonData);
		} catch (IOException e) {
			logger.error("Invalid request:" + e.getStackTrace());
			return "Error";
		}
		eventProducer.send("test-events1", jsonData);
		return "success";
	}
	
	/**
	 * To view the live data
	 * @param model
	 * @return
	 */
	 @RequestMapping(value = "/dashboard/all", method = RequestMethod.GET)  
		public String allData(ModelMap model) {
		 DataStore mongo = null;
		 try {
				mongo = MongoDataStore.getInstance(MONGO_HOST,MONGO_PORT );
				System.out.println("mongo: "+mongo);
			} catch (UnknownHostException e) {
				logger.error("Error in connection to mongoDb" + e.getStackTrace());
				model.addAttribute("message", "Invalid request");
				return VIEW_DATA;
			}
	        model.addAttribute("eventList", mongo.getAll()); 
	        System.out.println("Controller:"+mongo.getAll());
	        return VIEW_DATA;  
	 }
}
