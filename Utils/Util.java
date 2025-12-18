package com.inzeph.EmployeeManagement.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util extends HashMap<Object, Object> {
    Logger logger = LoggerFactory.getLogger(Util.class);

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj == null ? new int[0] : responseObj);
        return new ResponseEntity<>(map, status);
    }

    public static JSONObject convertJSON(Object src, Object dest, String field) throws IOException {
        Gson gson = new Gson();
        src = gson.toJson(src);
        dest = gson.toJson(dest);

        ObjectMapper mapper = new ObjectMapper();
        JSONObject target = mapper.readValue((String) src, JSONObject.class);
        JSONObject source = mapper.readValue((String) dest, JSONObject.class);
        target.put(field, source);
        return target;
    }

    public static Object removeJSONFields(Object src, List<String> fields) throws JsonProcessingException {
        Gson gson = new Gson();
        src = gson.toJson(src);

        ObjectMapper mapper = new ObjectMapper();
        JSONObject target = mapper.readValue((String) src, JSONObject.class);
        for (String field : fields) {
            target.remove(field);
        }
        return target;
    }
}
