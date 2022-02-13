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
@Entity
@Table(name = "tester")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tester {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String country;

    private String lastLogin;

    public Tester(Long id, String firstName, String lastName, String country, String lastLogin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.lastLogin = lastLogin;
    }

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "tester_device",
            joinColumns = @JoinColumn(name = "tester_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id")
    )
    @JsonIgnore
    private Set<Device> devices = new HashSet<>();

    @OneToMany(
            mappedBy = "tester",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<Bug> bugs = new HashSet<>();

    public void addDevice(Device device) {
        devices.add(device);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tester)) return false;
        return id != null && id.equals(((Tester) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
