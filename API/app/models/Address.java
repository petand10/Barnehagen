package models;

import javax.persistence.*;

import org.codehaus.jackson.JsonNode;
import play.db.ebean.*;

@Entity
public class Address extends Model{

    @Id
    public Long id;

    public String street;

    public Integer postal;

    public String postalArea;

    public static Address create(JsonNode root){
        if(root == null){
            throw new NullPointerException("Failed to read JSON root");
        }

        Address address = new Address();
        address.street = root.get("street").asText();
        address.postal = root.get("postal").asInt();
        address.postalArea = root.get("postalArea").asText();

        address.save();
        return address;
    }
}
