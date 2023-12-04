package com.scaler.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Name;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name="shows")
public class Show extends  BaseModel {
    @ManyToOne
    private Screen screen;
    @ManyToOne
    private Movie movie;
    private Date startTime;
    private Date endTime;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;
}
