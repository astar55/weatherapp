package models;

import javax.persistence.*;

@Entity
public class ZipCode {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public String Id;

    public String zipcode;

    @Column(columnDefinition = "longvarchar")
    public String htmldata;


}
