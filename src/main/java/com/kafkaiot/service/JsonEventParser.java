package com.kafkaiot.service;

import com.kafkaiot.model.SenlabHEntity;
import com.kafkaiot.model.SenlabMEntity;
import com.kafkaiot.model.SenlabTEntity;
import com.kafkaiot.pojo.DevEUI;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

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

    private int getPacketNumber(String hexData) {
        return Integer.parseUnsignedInt(hexData.substring(0, 2), 16);
    }

    private float parseFloat(String hexData, int startPos, int endPos) {
        return Float.intBitsToFloat(Integer.parseInt(hexData.substring(startPos, endPos), 16));
    }

    public Map<String, Object> getPacketData(String hexData) {
        Map<String, Object> result = new HashMap<>();

//Лог. адрес Физ. адрес Название параметра Тип Номер пакета Позиция Размер
        switch (getPacketNumber(hexData)) {
            case 1:
//uint 1 0 1
//300001 0x0000 Текущее время в сек ulong 1 1 4
                result.put("time", Long.parseUnsignedLong(hexData.substring(2, 10), 16));
//300003 0x0002 Значение канала Qt1 float 1 5 4
                result.put("Qt1", parseFloat(hexData, 10, 18));
//300005 0x0004 Значение канала Qt2 float 1 9 4
                result.put("Qt2", parseFloat(hexData, 18, 26));
//300007 0x0006 Значение канала Qt3 float 1 13 4
                result.put("Qt3", parseFloat(hexData, 26, 34));
//300009 0x0008 Значение канала Qt4 float 1 17 4
                result.put("Qt4", parseFloat(hexData, 34, 42));
//300011 0x000A Значение канала dQt1 float 1 21 4
                result.put("dQt1", parseFloat(hexData, 42, 50));
//300013 0x000C Значение канала dQt3 float 1 25 4
                result.put("dQt3", parseFloat(hexData, 50, 58));
//300015 0x000E Значение канала Wt1 float 1 29 4
                result.put("Wt1", parseFloat(hexData, 58, 66));
//300017 0x0010 Значение канала Wt2 float 1 33 4
                result.put("Wt2", parseFloat(hexData, 66, 74));
//300019 0x0012 Значение канала Wt3 float 1 37 4
                result.put("Wt3", parseFloat(hexData, 74, 82));
//300021 0x0014 Значение канала Wt4 float 1 41 2
                result.put("Wt4", parseFloat(hexData, 82, 86));
                break;
            case 2:
//uint 2 0 1
//300021 0x0014 Значение канала Wt4 float 2 1 2
                result.put("Wt4", parseFloat(hexData, 2, 6));
//300023 0x0016 Значение канала T1 float 2 3 4
                result.put("T1", parseFloat(hexData, 6, 14));
//300025 0x0018 Значение канала T2 float 2 7 4
                result.put("T2", parseFloat(hexData, 14, 22));
//300027 0x001A Значение канала T3 float 2 11 4
                result.put("T3", parseFloat(hexData, 22, 30));
//300029 0x001C Значение канала T4 float 2 15 4
                result.put("T4", parseFloat(hexData, 30, 38));
//300031 0x001E Значение канала Qo1 float 2 19 4
                result.put("Qo1", parseFloat(hexData, 38, 46));
//300033 0x0020 Значение канала Qo2 float 2 23 4
                result.put("Qo2", parseFloat(hexData, 46, 54));
//300035 0x0022 Значение канала Qo3 float 2 27 4
                result.put("Qo3", parseFloat(hexData, 54, 62));
//300037 0x0024 Значение канала Qo4 float 2 31 4
                result.put("Qo4", parseFloat(hexData, 62, 70));
//300039 0x0026 Значение канала Qm1 float 2 35 4
                result.put("Qm1", parseFloat(hexData, 70, 78));
//300041 0x0028 Значение канала Qm2 float 2 39 4
                result.put("Qm2", parseFloat(hexData, 78, 86));
                break;
            case 3:
//uint 3 0 1
//300043 0x002A Значение канала Qm3 float 2 1 4
                result.put("Qm3", parseFloat(hexData, 2, 10));
//300045 0x002C Значение канала Qm4 float 2 5 4
                result.put("Qm4", parseFloat(hexData, 10, 18));
//300047 0x002E Значение канала Gm1 float 2 9 4
                result.put("Gm1", parseFloat(hexData, 18, 26));
//300049 0x0030 Значение канала Gm2 float 2 13 4
                result.put("Gm2", parseFloat(hexData, 26, 34));
//300051 0x0032 Значение канала Gm3 float 3 17 4
                result.put("Gm3", parseFloat(hexData, 34, 42));
//300053 0x0034 Значение канала Gm4 float 3 21 4
                result.put("Gm4", parseFloat(hexData, 42, 50));
//300055 0x0036 Значение канала dGm1 float 3 25 4
                result.put("dGm1", parseFloat(hexData, 50, 58));
//300057 0x0038 Значение канала dGm3 float 3 29 4
                result.put("dGm3", parseFloat(hexData, 58, 66));
//300059 0x003A Значение канала P1 float 3 33 4
                result.put("P1", parseFloat(hexData, 66, 74));
//300061 0x003C Значение канала P2 float 3 37 4
                result.put("P2", parseFloat(hexData, 74, 82));
//300063 0x003E Значение канала P3 float 3 41 4
                result.put("P3", parseFloat(hexData, 82, 90));
                break;
        }
        return result;
    }

    public String getHexData() {
        return hexData;
    }

    public void setHexData(String hexData) {
        this.hexData = hexData;
    }
}
