package net.justwoofwolf.chromesmp.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.fabricmc.loader.api.FabricLoader;
import net.justwoofwolf.chromesmp.ChromeSMP;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.IOException;

public class JsonFileHelper {
    public static void writeJson(String[] fieldName, String[] value, String fileName) throws IOException {
        String filePath = FabricLoader.getInstance().getConfigDir().toString().concat("\\chromesmp\\" + fileName);

        File file = new File(filePath);
        file.getParentFile().mkdirs();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();

        for (int i = 0; i < fieldName.length; ++i) {
            jsonNode.put(fieldName[i], value[i]);
        }
        objectMapper.writeValue(file, jsonNode);

        ChromeSMP.LOGGER.info("Json file written to " + filePath);
    }

    public static String readJson(String fieldName, String fileName) throws IOException {
        String filePath = FabricLoader.getInstance().getConfigDir().toString().concat("\\chromesmp\\" + fileName);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File(filePath));

        return jsonNode.get(fieldName).asText();
    }
}