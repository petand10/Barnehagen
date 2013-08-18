package models;

import javax.persistence.*;
import javax.validation.constraints.Digits;

import org.codehaus.jackson.JsonNode;
import play.data.format.Formats;
import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Guardian extends Model{

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    @Formats.NonEmpty
    public Integer phone;

    @Constraints.Required
    @OneToOne
    public Address address;

    public static Guardian create(JsonNode root){
        if(root == null){
            throw new NullPointerException("Failed to read JSON root");
        }

        Guardian guardian = new Guardian();
        guardian.name = root.get("name").asText();
        guardian.phone = root.get("phone").asInt();
        guardian.address = Address.create(root.get("address"));

        guardian.save();
        return guardian;
    }
}
