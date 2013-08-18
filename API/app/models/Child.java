package models;

import java.util.*;
import javax.persistence.*;

import org.codehaus.jackson.JsonNode;
import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;


@Entity
public class Child extends Model{

    @Id
    public Long id;

    @Constraints.Required
    @Formats.NonEmpty
    public String name;

    @ManyToMany
    public List<Guardian> guardians;

    @Constraints.Required
    @Formats.NonEmpty
    public Long changed;

    public static Finder<Long, Child> finder = new Finder<Long, Child>(Long.class, Child.class);

    public static Child create(JsonNode root){
        if(root == null){
            throw new NullPointerException("Failed to read JSON root");
        }

        Child child = new Child();
        child.name = root.get("name").asText();
        child.changed = new Date().getTime();

        JsonNode guardianRoot = root.get("guardians");
        for(int i = 0; i < guardianRoot.size(); i++){
            child.guardians.add(Guardian.create(guardianRoot.get(i)));
        }

        child.save();
        child.saveManyToManyAssociations("guardians");
        return child;
    }

    public static Child update(Long id, JsonNode root){
        if(root == null){
            throw new NullPointerException("Failed to read JSON root");
        }

        Child child = finder.byId(id);
        if(child == null){
            throw new NullPointerException("Cant find child");
        }

        child.name = root.get("name").asText();
        child.changed = new Date().getTime();
        /*Iterator<Guardian> itr = child.guardians.iterator();
        while(itr.hasNext()){
            itr.next().delete();
        } */
        child.guardians.clear();

        JsonNode guardianRoot = root.get("guardians");
        for(int i = 0; i < guardianRoot.size(); i++){
            child.guardians.add(Guardian.create(guardianRoot.get(i)));
        }
        child.update();
        child.saveManyToManyAssociations("guardians");
        return child;
    }
}
