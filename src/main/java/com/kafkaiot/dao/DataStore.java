/**
 * 
 */
package com.kafkaiot.dao;

/**
 * @author kafkaiot
 *
 */
public interface DataStore<T> {

	public boolean storeRawEvent(String jsonData);

	public T getAll();

}
