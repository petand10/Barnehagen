package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.validation.*;


@Entity
public class Child extends Model{

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @ManyToMany
    public List<Guardian> guardians;

    public static Finder<Long, Child> finder = new Finder<Long, Child>(Long.class, Child.class);
}
