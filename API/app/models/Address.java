package models;

import javax.persistence.*;
import javax.validation.constraints.Null;

import play.db.ebean.*;

public class Address extends Model{
    @Id
    public Long id;

    @Null
    public String street;

    @Null
    public Integer postal;

    @Null
    public String postalArea;
}
