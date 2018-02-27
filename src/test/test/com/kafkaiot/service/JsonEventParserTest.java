package test.com.kafkaiot.service;

import com.kafkaiot.model.SenlabTEntity;
import com.kafkaiot.service.JsonEventParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonEventParserTest {

    private JsonEventParser eventParser;

    @BeforeEach
    void setUp() {
        eventParser = new JsonEventParser("{\"DevEUI_uplink\": {\"Time\": \"2017-07-21T10:32:41.664+02:00\",\"DevEUI\": \"70B3D580A0100545\",\"FPort\": \"3\",\"FCntUp\": \"5638\",\"ADRbit\": \"1\",\"MType\": \"2\",\"FCntDn\": \"1882\",\"payload_hex\": \"01eb835187040233\",\"mic_hex\": \"fa04bb78\",\"Lrcid\": \"00000125\",\"LrrRSSI\": \"-96.000000\",\"LrrSNR\": \"6.000000\",\"SpFact\": \"7\",\"SubBand\": \"G3\",\"Channel\": \"LC8\",\"DevLrrCnt\": \"2\",\"Lrrid\": \"FF010EC5\",\"Late\": \"0\",\"LrrLAT\": \"55.743526\",\"LrrLON\": \"37.634930\",\"Lrrs\": {\"Lrr\": [{\"Lrrid\": \"FF010EC5\",\"Chain\": \"0\",\"LrrRSSI\": \"-96.000000\",\"LrrSNR\": \"6.000000\",\"LrrESP\": \"-96.973228\"},{\"Lrrid\": \"0B0301F5\",\"Chain\": \"0\",\"LrrRSSI\": \"-107.000000\",\"LrrSNR\": \"7.500000\",\"LrrESP\": \"-107.710815\"}]},\"CustomerID\": \"100001964\",\"CustomerData\": {\"alr\":{\"pro\":\"SLABS/SENLAB\",\"ver\":\"1\"}},\"ModelCfg\": \"0\",\"AppSKey\": \"1cf0e7d861e7f36a69d236633ddaef75\",\"DevAddr\": \"05F67BC4\"}}");
    }

    @Test
    void getType() {
        org.junit.jupiter.api.Assertions.assertEquals(1, eventParser.getType());
    }

    @Test
    void parseSenlabHMessage() {
    }

    @Test
    void parseSenlabTMessage() {
        eventParser.setHexData("01FE5B8134018F");
        SenlabTEntity senlabTEntity = eventParser.parseSenlabTMessage();
        assertEquals(399, senlabTEntity.getTemperature());
    }

    @Test
    void parseSenlabMMessage() {
    }
}