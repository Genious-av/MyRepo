package m2m.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import m2m.entity.SensorEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(value = Processor.class)
public class SensorMapperService {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    Processor processor;

    @StreamListener(Processor.INPUT)
    public void takeSensorRawData(String sensorData) throws JsonProcessingException {
    	System.out.println(sensorData);
        JSONObject jsonObject = new JSONObject(clearString(sensorData));

        SensorEntity sensorEntity = new SensorEntity(
                jsonObject.getInt("sensorId"),
                getArrayOfDouble(jsonObject.getJSONArray("sensorValues")),
                jsonObject.getLong("timeStamp"),
                jsonObject.getString("sensorType")
        );

        String strSensorEntity = mapper.writeValueAsString(sensorEntity);

        processor.output().send(MessageBuilder.withPayload(strSensorEntity).build());
        System.out.println("sendMessage: " + strSensorEntity);
    }

    private String clearString(String strInput) {

        String result;

        result = strInput.replace("\\", "");
        int len = result.length();
        if (result.substring(0, 1).equals("\"") && result.substring(len - 1, len).equals("\"")) {
            result = result.substring(1, len - 1);
        }

        return result;
    }

    private double[] getArrayOfDouble (JSONArray jsonArray) {
        double[] values = new double[jsonArray.length()];

        for (int i = 0; i < values.length; i++) {
            values[i] = jsonArray.getDouble(i);
        }

        return values;
    }
}
