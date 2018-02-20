/**
 * 
 */
package com.kafkaiot.service;

/**
 * @author kafkaiot
 *
 */
public interface EventProducer {

	public boolean send(String topicName, String data);
	public void disconnect();
	
}
