package controllers;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

import models.*;
import helper.*;

public class ChildrenController extends Controller {

    /**
     * Controller function answering to the route /children/
     * @return HTTP request answer with json content
     */
    public static Result retrieveAll(){
        JsonNode node = null;
        try{
            List<Child> children = Child.finder.all();
            node = JsonBuilder.create(children, null);
        }catch(Exception e){
            JsonHeader header = new JsonHeader();
            header.error = JsonHeader.getError(1);
            node = JsonBuilder.create(null, header);
            return badRequest(node).as("application/json");
        }
        return ok(node).as("application/json");
    }

    public static Result retrieve(Long id){
        JsonNode node = null;
        try{
            Child child = Child.finder.byId(id);
            if(child == null){
                throw new Exception();
            }
            node = JsonBuilder.create(child, null);
        }catch(Exception e){
            JsonHeader header = new JsonHeader();
            header.error = JsonHeader.getError(1);
            node = JsonBuilder.create(null, header);
            return badRequest(node).as("application/json");
        }
        return ok(node).as("application/json");
    }

    public static Result retrieveSince(Long time){
        JsonNode node = null;
        try{
            List<Child> children =
                    Child.finder.where()
                            .ge("changed", time)
                            .findList();
            node = JsonBuilder.create(children, null);
        }catch(Exception e){
            JsonHeader header = new JsonHeader();
            header.error = JsonHeader.getError(1);
            node = JsonBuilder.create(null, header);
            return badRequest(node).as("application/json");
        }
        return ok(node).as("application/json");
    }


    public static Result find(String name){
        JsonNode node = null;
        try{
            List<Child> children =
                    Child.finder.where()
                    .eq("name", name)
                    .findList();
            if(children == null){
                throw new Exception();
            }
            node = JsonBuilder.create(children, null);
        }catch(Exception e){
            JsonHeader header = new JsonHeader();
            header.error = JsonHeader.getError(1);
            node = JsonBuilder.create(null, header);
            return badRequest(node).as("application/json");
        }
        return ok(node).as("application/json");
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result persist(){
        JsonNode request = request().body().asJson();

        Child child = null;
        try{
            if(request == null){
                throw new NullPointerException("Failed to parse JSON");
            }
            child = Child.create(request);
        }catch(Exception e){
            JsonHeader header = new JsonHeader();
            header.error = JsonHeader.getError(3);
            JsonNode node = JsonBuilder.create(child, header);
            return badRequest(node).as("application/json");
        }
        return created(JsonBuilder.create(child, null)).as("application/json");
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result update(Long id){
        JsonNode request = request().body().asJson();

        Child child = null;
        try{
            if(request == null){
                throw new NullPointerException("Failed to parse JSON");
            }
            child = Child.finder.byId(id);
            if(child == null){
                throw new Exception();
            }
            child = Child.update(child.id, request);
        }catch(Exception e){
            JsonHeader header = new JsonHeader();
            header.error = JsonHeader.getError(3) + " " + e.getMessage();
            JsonNode node = JsonBuilder.create(child, header);
            return badRequest(node).as("application/json");
        }
        return created(JsonBuilder.create(child, null)).as("application/json");
    }

    public static Result delete(Long id){
        JsonNode node = null;
        try{
            Child child = Child.finder.byId(id);
            if(child == null){
                throw new Exception();
            }
            node = Json.toJson(child);
            child.deleteManyToManyAssociations("guardians");
            child.delete();
        }catch(Exception e){
            JsonHeader header = new JsonHeader();
            header.error = JsonHeader.getError(1) + " " + e.getMessage();
            node = JsonBuilder.create(null, header);
            return badRequest(node).as("application/json");
        }
        return ok(node).as("application/json");
    }
}
