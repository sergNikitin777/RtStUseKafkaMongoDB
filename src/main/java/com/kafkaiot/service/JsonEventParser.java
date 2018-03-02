package com.kafkaiot.service;

import com.kafkaiot.model.SenlabHEntity;
import com.kafkaiot.model.SenlabMEntity;
import com.kafkaiot.model.SenlabTEntity;
import com.kafkaiot.pojo.DevEUI;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonEventParser {
/*
{
	"DevEUI_uplink": {
		"Time": "2017-07-21T10:32:41.664+02:00",
		"DevEUI": "70B3D580A0100545",
		"FPort": "3",
		"FCntUp": "5638",
		"ADRbit": "1",
		"MType": "2",
		"FCntDn": "1882",
		"payload_hex": "01eb835187040233",
		"mic_hex": "fa04bb78",
		"Lrcid": "00000125",
		"LrrRSSI": "-96.000000",
		"LrrSNR": "6.000000",
		"SpFact": "7",
		"SubBand": "G3",
		"Channel": "LC8",
		"DevLrrCnt": "2",
		"Lrrid": "FF010EC5",
		"Late": "0",
		"LrrLAT": "55.743526",
		"LrrLON": "37.634930",
		"Lrrs": {
			"Lrr": [{
				"Lrrid": "FF010EC5",
				"Chain": "0",
				"LrrRSSI": "-96.000000",
				"LrrSNR": "6.000000",
				"LrrESP": "-96.973228"
			},
			{
				"Lrrid": "0B0301F5",
				"Chain": "0",
				"LrrRSSI": "-107.000000",
				"LrrSNR": "7.500000",
				"LrrESP": "-107.710815"
			}]
		},
		"CustomerID": "100001964",
		"CustomerData": {
			"alr": {
				"pro": "SLABS/SENLAB",
				"ver": "1"
			}
		},
		"ModelCfg": "0",
		"AppSKey": "1cf0e7d861e7f36a69d236633ddaef75",
		"DevAddr": "05F67BC4"
	}
}
*/

    private DevEUI source;
    private String hexData;

    public JsonEventParser(String eventMessage) {
        ObjectMapper om = new ObjectMapper();
        try {
            source = om.readValue(eventMessage, DevEUI.class);
            //hexData =  javax.xml.bind.DatatypeConverter.parseHexBinary(source.getDevEUIUplink().getPayloadHex());
            hexData = source.getDevEUIUplink().getPayloadHex();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getFport() {
        return Integer.parseInt(source.getDevEUIUplink().getFPort());
    }

    public int getType() {
        return Integer.parseInt(hexData.substring(0, 2), 16);
    }

    public SenlabHEntity parseSenlabHMessage() {
        return new SenlabHEntity(source.getDevEUIUplink().getTime(), source.getDevEUIUplink().getDevEUI(), source.getDevEUIUplink().getFPort(), getSenlabHFport3Temperature(), getSenlabHFport3Humidity());
    }

    public SenlabTEntity parseSenlabTMessage() {
        return new SenlabTEntity(source.getDevEUIUplink().getTime(), source.getDevEUIUplink().getDevEUI(), source.getDevEUIUplink().getFPort(), getSenlabTFport3Temperature());
    }

    public SenlabMEntity parseSenlabMMessage() {
        return new SenlabMEntity(source.getDevEUIUplink().getTime(), source.getDevEUIUplink().getDevEUI(), source.getDevEUIUplink().getFPort(), getSenlabMFport3Pulses());
    }

    private int getSenlabHFport3Temperature() {
        int startPos = hexData.length() - 6;
        int endPos = hexData.length() - 2;
        return Integer.parseInt(hexData.substring(startPos, endPos), 16);
    }

    private int getSenlabHFport3Humidity() {
        int startPos = hexData.length() - 2;
        int endPos = hexData.length();
        return Integer.parseInt(hexData.substring(startPos, endPos), 16);
    }

    private int getSenlabTFport3Temperature() {
        int startPos = hexData.length() - 4;
        int endPos = hexData.length();
        return Integer.parseInt(hexData.substring(startPos, endPos), 16);
    }

    private int getSenlabMFport3Pulses() {
        int startPos = hexData.length() - 8;
        int endPos = hexData.length();
        return Integer.parseUnsignedInt(hexData.substring(startPos, endPos), 16);
    }

    public String getHexData() {
        return hexData;
    }

    public void setHexData(String hexData) {
        this.hexData = hexData;
    }
}
