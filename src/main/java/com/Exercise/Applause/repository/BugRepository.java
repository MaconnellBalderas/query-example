package com.Exercise.Applause.repository;

import com.Exercise.Applause.model.Bug;
import com.Exercise.Applause.model.Tester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugRepository extends JpaRepository<Bug, Long> {

    List<Bug> findAllByTesterCountryInAndDeviceDescriptionIn(List<String> countries, List<String> devices);

    List<Bug> findAllByTesterCountryIn(List<String> countries);

    List<Bug> findAllByDeviceDescriptionIn(List<String> devices);
}
