package com.Exercise.Applause.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bug {

    @Id
    private Long id;

    public Bug(Long id, Device device, Tester tester) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deviceId")
    private Device device;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "testerId")
    private Tester tester;

    public void addDevice(Device device) {
        this.device = device;
        device.getBugs().add(this);
    }

    public void addTester(Tester tester) {
        this.tester = tester;
        tester.getBugs().add(this);
        tester.getDevices().add(device);
    }

}
