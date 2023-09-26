package com.stg.incidentvehicleinformationrestservices.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incident {
    @Id
    @GeneratedValue
    private Long id;
    private String logNumber;
    private String dcPrefix;
    private String dcName;
    private String dcFault;
    private String dcAvail;
    private String locFrnt;
    private String locRear;
    private String locLeft;
    private String locRigh;
    private String partRec;
    private String partmemo;
    private String codedBy;
    private String fieldInv;

}
