package com.webdevgroup.sp2101webdevegroupserverjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "events")
public class Event{
    @Id
    private Long id;
    private String title;
    private Date datetime_local;
    @ManyToMany
    @NotNull
    private List<Performer> performers;
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    private Venue venue;
    private String short_title;
    private Date datetime_utc;
    private double score;
    private String type;
    private int likes;
    private int attending;
    @OneToMany
    @JsonIgnore
    private Set<Comment> comments;
}
