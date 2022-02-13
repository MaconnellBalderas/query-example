package com.Exercise.Applause.controller;

import com.Exercise.Applause.model.Bug;
import com.Exercise.Applause.model.Device;
import com.Exercise.Applause.model.Request;
import com.Exercise.Applause.model.Tester;
import com.Exercise.Applause.payload.AddDevicePost;
import com.Exercise.Applause.payload.ExperienceResponse;
import com.Exercise.Applause.repository.BugRepository;
import com.Exercise.Applause.repository.DeviceRepository;
import com.Exercise.Applause.repository.TesterRepository;
import com.Exercise.Applause.service.BugsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;

@RestController
public class BugsController {

    private BugsService bugsService;

    private BugRepository bugRepository;

    private DeviceRepository deviceRepository;

    private TesterRepository testerRepository;

    public BugsController(BugsService bugsService, BugRepository bugRepository, DeviceRepository deviceRepository, TesterRepository testerRepository) {
        this.bugsService = bugsService;
        this.bugRepository = bugRepository;
        this.deviceRepository = deviceRepository;
        this.testerRepository = testerRepository;
    }

    @GetMapping("/experience")
    public ExperienceResponse searchForExperience(@RequestParam(required = false) List<String> countries,
                                                  @RequestParam(required = false) List<String> devices) {
        return bugsService.getBugCount(countries, devices);
    }

    @GetMapping("/bugs")
    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    @GetMapping("/devices")
    public List<Device> getAllDevice() {
//        return bugRepository.findAll();
        return deviceRepository.findAll();
    }

    @PostMapping("/testers/{testerId}/device")
    public Tester addDevice(@PathVariable Long testerId, @RequestBody AddDevicePost body) {
        Tester tester = testerRepository.findById(testerId).get();
        Device device = deviceRepository.findById(body.getDeviceId()).get();

        tester.addDevice(device);
        return testerRepository.save(tester);
    }

    @GetMapping("/testers")
    public List<Tester> getAllTesters() {
//        return bugRepository.findAll();
        return testerRepository.findAll();
    }

}
