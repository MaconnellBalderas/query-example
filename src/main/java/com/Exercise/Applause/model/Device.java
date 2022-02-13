package com.Exercise.Applause.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device {

    @Id
    private Long id;

    private String description;

    public Device(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "devices")
    private Set<Tester> testers = new HashSet<>();

    @OneToMany(
            mappedBy = "device",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<Bug> bugs = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device)) return false;
        return id != null && id.equals(((Device) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
