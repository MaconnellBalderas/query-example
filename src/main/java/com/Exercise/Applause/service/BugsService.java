package com.Exercise.Applause.service;

import com.Exercise.Applause.model.Bug;
import com.Exercise.Applause.model.Tester;
import com.Exercise.Applause.payload.ExperienceProfile;
import com.Exercise.Applause.payload.ExperienceResponse;
import com.Exercise.Applause.repository.BugRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BugsService {

    private BugRepository bugRepo;

    public BugsService(BugRepository bugRepo) {
        this.bugRepo = bugRepo;
    }

    public ExperienceResponse getBugCount(List<String> countries, List<String> devices) {
        List<Bug> bugs = null;
        if(hasFilterParameters(countries) && hasFilterParameters(devices)) {
            bugs =  bugRepo.findAllByTesterCountryInAndDeviceDescriptionIn(countries, devices);
        } else if(hasFilterParameters(countries) && !hasFilterParameters(devices)) {
            bugs =  bugRepo.findAllByTesterCountryIn(countries);
        } else if(!hasFilterParameters(countries) && hasFilterParameters(devices)) {
            bugs =  bugRepo.findAllByDeviceDescriptionIn(devices);
        } else {
            bugs = bugRepo.findAll();
        }

        HashMap<Tester, Integer> orderedExp = new HashMap<>();
        for(Bug bug : bugs) {
            Tester selectedTester = bug.getTester();
            if(orderedExp.containsKey(selectedTester)) {
                Integer bugCount = orderedExp.get(selectedTester);
                orderedExp.put(selectedTester, bugCount + 1);
            } else {
                orderedExp.put(selectedTester, 1);
            }
        }
        List<ExperienceProfile> experienceProfiles = orderedExp.entrySet().stream()
                .map( entry -> new ExperienceProfile(entry.getKey().getFirstName() + " " + entry.getKey().getFirstName(), entry.getValue()))
                .collect(Collectors.toList());
        experienceProfiles.sort(Comparator.comparingLong(ExperienceProfile::getBugsFiled).reversed());
        return new ExperienceResponse(experienceProfiles);
    }

    private boolean hasFilterParameters(List<String> paramList) {
        return !(paramList == null || paramList.contains("All"));
    }

}
