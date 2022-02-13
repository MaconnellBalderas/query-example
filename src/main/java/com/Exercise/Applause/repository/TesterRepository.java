package com.Exercise.Applause.repository;

import com.Exercise.Applause.model.Bug;
import com.Exercise.Applause.model.Tester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TesterRepository extends JpaRepository<Tester, Long> {

    List<Bug> findAllByCountryIn(List<String> countries);

    List<Tester> findAllByCountryInAndDevicesDescriptionIn(List<String> countries, List<String> devices);

}
