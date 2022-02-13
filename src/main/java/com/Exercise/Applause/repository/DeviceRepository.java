package com.Exercise.Applause.repository;

import com.Exercise.Applause.model.Bug;
import com.Exercise.Applause.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
