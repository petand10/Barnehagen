package models;

import javax.persistence.*;
import javax.validation.constraints.Digits;

import play.db.ebean.*;
import play.data.validation.*;

import java.util.List;

public class Guardian extends Model{

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    @Digits(integer = 8, fraction = 0)
    public Integer phone;

    @Constraints.Required
    @OneToOne
    public Address address;

    @ManyToMany
    public List<Child> children;
}
