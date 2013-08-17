package controllers;

import java.util.*;

import helper.JsonBuilder;
import org.codehaus.jackson.JsonNode;
import play.libs.Json;
import play.mvc.*;

import models.*;

public class ChildrenController extends Controller {

    /**
     * Controller function answering to the route /children/
     * @return HTTP request answer with json content
     */
    public static Result get(){
        JsonNode node = null;
        try{
            List<Child> children = Child.finder.all();
            node = JsonBuilder.create(children);
        }catch(Exception e){
            node = JsonBuilder.create(null);
            return Results.badRequest(node);
        }
        return Results.ok(node);
    }

    public static Result getChild(Long id){
        return Results.ok();
    }

    public static Result getLatest(Long time){
        return Results.ok();
    }

    public static Result find(String name){
        return Results.ok();
    }

    public static Result create(){
        return Results.ok();
    }

    public static Result update(Long id){
        return Results.ok();
    }

    public static Result delete(Long id){
        return Results.ok();
    }
}
