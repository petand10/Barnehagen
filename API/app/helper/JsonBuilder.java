package helper;

import org.codehaus.jackson.JsonNode;
import play.libs.Json;

public class JsonBuilder {
    public static JsonNode create(Object obj, JsonHeader header) {
        JsonHeader h = new JsonHeader();
        if(header != null){
            h = header;
        }

        String str = "{}";
        if(obj != null){
            str = Json.stringify(Json.toJson(obj));
        }

        JsonNode node = Json.parse(
                "{" +
                "\"header\":" + Json.stringify(Json.toJson(h)) +
                ",\"content\":" + str +
                "}"
        );

        return node;
    }
}
