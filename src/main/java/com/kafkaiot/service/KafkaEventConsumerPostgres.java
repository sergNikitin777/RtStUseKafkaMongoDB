/**
 *
 */
package com.kafkaiot.service;

import com.kafkaiot.controller.BaseController;
import com.kafkaiot.model.SenlabHEntity;
import com.kafkaiot.model.SenlabMEntity;
import com.kafkaiot.model.SenlabTEntity;
import com.kafkaiot.repository.SenlabHEntityDao;
import com.kafkaiot.repository.SenlabMEntityDao;
import com.kafkaiot.repository.SenlabTEntityDao;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

/**
 * @author svnikitin
 */
@Service
public class KafkaEventConsumerPostgres extends Thread implements EventConsumer {

    final static String clientId = "SarojKafkaClient";
    final static String TOPIC = "test-events";
    private ConsumerConnector consumerConnector;
    private ExecutorService executor;
    private final static org.slf4j.Logger logger = LoggerFactory
            .getLogger(BaseController.class);


    @Autowired
    SenlabTEntityDao senlabTEntityDao;
    @Autowired
    SenlabMEntityDao senlabMEntityDao;
    @Autowired
    SenlabHEntityDao senlabHEntityDao;

    public static void main(String[] argv) {
        System.out.println("start");
        BasicConfigurator.configure();
        KafkaEventConsumerPostgres kafkaConsumer = new KafkaEventConsumerPostgres();
        kafkaConsumer.start();
    }

    public KafkaEventConsumerPostgres() {

        Properties props = new Properties();
        props.put("zookeeper.connect", "10.59.1.210:2181");
        props.put("group.id", "test-group-postgres");
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "10000");
        props.put("auto.commit.interval.ms", "10000");
        ConsumerConfig consumerConfig = new ConsumerConfig(props);
        consumerConnector = Consumer
                .createJavaConsumerConnector(consumerConfig);

    }

    /**
     * This thread will pull the events from the topic and insert into mongo DB
     */
    @Async
    public void run() {

        System.out.println("inside run");

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(TOPIC, new Integer(1));

        System.out.println("start Postgres Consumer");

        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> streams = consumerMap.get(TOPIC).get(0);
        ConsumerIterator<byte[], byte[]> it = streams.iterator();
        while (it.hasNext()) {
            try {
                String data = new String(it.next().message());
                System.out.println(data);
                RestTemplate restTemplate;
                final String urlTermo = "http://10.59.1.210:8080/charts/sensors/thermo";
                final String urlCount = "http://10.59.1.210:8080/charts/sensors/count";
                final String urlHumidity = "http://10.59.1.210:8080/charts/sensors/humidity";
                JsonEventParser eventParser = new JsonEventParser(data);
                if (eventParser.getFport() != 3)
                    continue;
                switch (eventParser.getType()) {
                    case 1:
                        try {
                            SenlabTEntity senlabTEntity = eventParser.parseSenlabTMessage();
                            System.out.println(senlabTEntity.toString());
                            senlabTEntityDao.save(senlabTEntity);
                            restTemplate = new RestTemplate();

                            String requestJson = "{\"id\": \"33\",\"value\": " + senlabTEntity.getTempC() + "}";
                            HttpHeaders headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
                            String answer = restTemplate.postForObject(urlTermo, entity, String.class);
                            System.out.println(answer);
                        } catch (Exception e) {
                            logger.error("SenlabTEntity ", e);
                        }
                        break;
                    case 2:
                        try {
                            SenlabMEntity senlabMEntity = eventParser.parseSenlabMMessage();
                            System.out.println(senlabMEntity.toString());
                            senlabMEntityDao.save(senlabMEntity);
                            restTemplate = new RestTemplate();

                            String requestJson = "{\"id\": \"33\",\"value\": " + senlabMEntity.getCount() + "}";
                            HttpHeaders headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);

                            HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
                            String answer = restTemplate.postForObject(urlCount, entity, String.class);
                            System.out.println(answer);
                        } catch (Exception e) {
                            logger.error("SenlabMEntity ", e);
                        }
                        break;
                    case 3:
                        try {
                            SenlabHEntity senlabHEntity = eventParser.parseSenlabHMessage();
                            System.out.println(senlabHEntity.toString());
                            senlabHEntityDao.save(senlabHEntity);
                            restTemplate = new RestTemplate();
                            String requestJson = "{\"id\": \"33\",\"value\": " + senlabHEntity.getTempC() + "}";
                            HttpHeaders headers = new HttpHeaders();
                            headers.setContentType(MediaType.APPLICATION_JSON);
                            HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
                            String answer = restTemplate.postForObject(urlTermo, entity, String.class);
                            System.out.println(answer);

                            requestJson = "{\"id\": \"33\",\"value\": " + senlabHEntity.getHumidity() + "}";
                            entity = new HttpEntity<String>(requestJson, headers);
                            answer = restTemplate.postForObject(urlHumidity, entity, String.class);
                            System.out.println(answer);
                        } catch (Exception e) {
                            logger.error("", e);
                        }
                }
                //store.storeRawEvent(data);
            } catch (Exception e) {
                logger.error(
                        "Throwing Exception while inserting data to Mongo DB",
                        e);
            }
        }
    }
}
