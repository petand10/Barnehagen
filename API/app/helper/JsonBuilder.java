package helper;

import org.codehaus.jackson.JsonNode;
import play.libs.Json;

public class JsonBuilder {
    public static JsonNode create(Object obj) {
        JsonNode node = Json.parse(
                "{" +
                "header: {" +
                         "error: {}"  +
                        "}" +
                "content:" + Json.stringify(Json.toJson(obj)) +
                "}"
        );

        return node;
    }
}
