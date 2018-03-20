package test.com.kafkaiot.service;

import com.google.common.collect.ImmutableList;
import com.kafkaiot.model.IM2300;
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

    @Test
    void parseIM2300() {
        String p1 = "{\"DevEUI_uplink\" : {\n" +
                "    \"Time\" : \"2018-03-19T15:39:38.84+00:00\",\n" +
                "    \"DevEUI\" : \"363335386C357A0E\",\n" +
                "    \"FPort\" : \"3\",\n" +
                "    \"FCntUp\" : \"12354\",\n" +
                "    \"ADRbit\" : \"1\",\n" +
                "    \"MType\" : \"2\",\n" +
                "    \"FCntDn\" : \"788\",\n" +
                "    \"payload_hex\" : \"010013ac6447d70562417699fe431e7fea3f002bb04337a38b41bcd239482438770000000045ecbc0445ff\",\n" +
                "    \"mic_hex\" : \"1f3be5d4\",\n" +
                "    \"Lrcid\" : \"00000241\",\n" +
                "    \"LrrRSSI\" : \"-105.000000\",\n" +
                "    \"LrrSNR\" : \"10.000000\",\n" +
                "    \"SpFact\" : \"7\",\n" +
                "    \"SubBand\" : \"G2\",\n" +
                "    \"Channel\" : \"LC7\",\n" +
                "    \"DevLrrCnt\" : \"1\",\n" +
                "    \"Lrrid\" : \"FF010EC9\",\n" +
                "    \"Late\" : \"0\",\n" +
                "    \"LrrLAT\" : \"57.922054\",\n" +
                "    \"LrrLON\" : \"56.139839\",\n" +
                "    \"Lrrs\" : {\n" +
                "      \"Lrr\" : [{\n" +
                "          \"Lrrid\" : \"FF010EC9\",\n" +
                "          \"Chain\" : \"0\",\n" +
                "          \"LrrRSSI\" : \"-105.000000\",\n" +
                "          \"LrrSNR\" : \"10.000000\",\n" +
                "          \"LrrESP\" : \"-105.413925\"\n" +
                "        }]\n" +
                "    },\n" +
                "    \"CustomerID\" : \"1100000039\",\n" +
                "    \"CustomerData\" : {\n" +
                "      \"alr\" : {\n" +
                "        \"pro\" : \"LORA/Generic\",\n" +
                "        \"ver\" : \"1\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"ModelCfg\" : \"0\",\n" +
                "    \"InstantPER\" : \"0.000000\",\n" +
                "    \"MeanPER\" : \"0.019322\",\n" +
                "    \"DevAddr\" : \"6890CC32\"\n" +
                "  }}";

        String p2 = "{\"DevEUI_uplink\" : {\n" +
                "    \"Time\" : \"2018-03-19T15:39:48.113+00:00\",\n" +
                "    \"DevEUI\" : \"363335386C357A0E\",\n" +
                "    \"FPort\" : \"3\",\n" +
                "    \"FCntUp\" : \"12355\",\n" +
                "    \"ADRbit\" : \"1\",\n" +
                "    \"MType\" : \"2\",\n" +
                "    \"FCntDn\" : \"788\",\n" +
                "    \"payload_hex\" : \"022baf00000000000000000000000000000000000000000000000000000000000000000000000000000000\",\n" +
                "    \"mic_hex\" : \"0aef4e05\",\n" +
                "    \"Lrcid\" : \"00000241\",\n" +
                "    \"LrrRSSI\" : \"-104.000000\",\n" +
                "    \"LrrSNR\" : \"10.000000\",\n" +
                "    \"SpFact\" : \"7\",\n" +
                "    \"SubBand\" : \"G2\",\n" +
                "    \"Channel\" : \"LC8\",\n" +
                "    \"DevLrrCnt\" : \"1\",\n" +
                "    \"Lrrid\" : \"FF010EC9\",\n" +
                "    \"Late\" : \"0\",\n" +
                "    \"LrrLAT\" : \"57.922054\",\n" +
                "    \"LrrLON\" : \"56.139839\",\n" +
                "    \"Lrrs\" : {\n" +
                "      \"Lrr\" : [{\n" +
                "          \"Lrrid\" : \"FF010EC9\",\n" +
                "          \"Chain\" : \"0\",\n" +
                "          \"LrrRSSI\" : \"-104.000000\",\n" +
                "          \"LrrSNR\" : \"10.000000\",\n" +
                "          \"LrrESP\" : \"-104.413925\"\n" +
                "        }]\n" +
                "    },\n" +
                "    \"CustomerID\" : \"1100000039\",\n" +
                "    \"CustomerData\" : {\n" +
                "      \"alr\" : {\n" +
                "        \"pro\" : \"LORA/Generic\",\n" +
                "        \"ver\" : \"1\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"ModelCfg\" : \"0\",\n" +
                "    \"InstantPER\" : \"0.000000\",\n" +
                "    \"MeanPER\" : \"0.018356\",\n" +
                "    \"DevAddr\" : \"6890CC32\"\n" +
                "  }}";

        String p3 = "{\"DevEUI_uplink\" : {\n" +
                "    \"Time\" : \"2018-03-19T15:39:58.0+00:00\",\n" +
                "    \"DevEUI\" : \"363335386C357A0E\",\n" +
                "    \"FPort\" : \"3\",\n" +
                "    \"FCntUp\" : \"12356\",\n" +
                "    \"ADRbit\" : \"1\",\n" +
                "    \"MType\" : \"2\",\n" +
                "    \"FCntDn\" : \"788\",\n" +
                "    \"payload_hex\" : \"03000000000000000000000000000000000000000000000000000000000000000000000000000000000000\",\n" +
                "    \"mic_hex\" : \"6b963ff7\",\n" +
                "    \"Lrcid\" : \"00000241\",\n" +
                "    \"LrrRSSI\" : \"-107.000000\",\n" +
                "    \"LrrSNR\" : \"8.000000\",\n" +
                "    \"SpFact\" : \"7\",\n" +
                "    \"SubBand\" : \"G2\",\n" +
                "    \"Channel\" : \"LC5\",\n" +
                "    \"DevLrrCnt\" : \"1\",\n" +
                "    \"Lrrid\" : \"FF010EC9\",\n" +
                "    \"Late\" : \"0\",\n" +
                "    \"LrrLAT\" : \"57.922054\",\n" +
                "    \"LrrLON\" : \"56.139839\",\n" +
                "    \"Lrrs\" : {\n" +
                "      \"Lrr\" : [{\n" +
                "          \"Lrrid\" : \"FF010EC9\",\n" +
                "          \"Chain\" : \"0\",\n" +
                "          \"LrrRSSI\" : \"-107.000000\",\n" +
                "          \"LrrSNR\" : \"8.000000\",\n" +
                "          \"LrrESP\" : \"-107.638924\"\n" +
                "        }]\n" +
                "    },\n" +
                "    \"CustomerID\" : \"1100000039\",\n" +
                "    \"CustomerData\" : {\n" +
                "      \"alr\" : {\n" +
                "        \"pro\" : \"LORA/Generic\",\n" +
                "        \"ver\" : \"1\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"ModelCfg\" : \"0\",\n" +
                "    \"InstantPER\" : \"0.000000\",\n" +
                "    \"MeanPER\" : \"0.017438\",\n" +
                "    \"DevAddr\" : \"6890CC32\"\n" +
                "  }}";
        IM2300 item = JsonEventParser.getPacketData(ImmutableList.of(new JsonEventParser(p1).getSource().getDevEUIUplink().getPayloadHex(),
                new JsonEventParser(p2).getSource().getDevEUIUplink().getPayloadHex(),
                new JsonEventParser(p3).getSource().getDevEUIUplink().getPayloadHex()));
    }
}