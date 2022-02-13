package com.Exercise.Applause;

import com.Exercise.Applause.model.Bug;
import com.Exercise.Applause.model.BugDTO;
import com.Exercise.Applause.model.Device;
import com.Exercise.Applause.model.Tester;
import com.Exercise.Applause.repository.BugRepository;
import com.Exercise.Applause.repository.DeviceRepository;
import com.Exercise.Applause.repository.TesterRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ApplauseApplication {

	@Autowired
	private BugRepository bugRepo;

	@Autowired
	private DeviceRepository deviceRepo;

	@Autowired
	private TesterRepository testerRepo;

	public static void main(String[] args) {
		SpringApplication.run(ApplauseApplication.class, args);
	}

	@EventListener(ContextRefreshedEvent.class)
	public void initializeDatabase() throws IOException, URISyntaxException {
		List<Device> deviceList = Arrays.asList(
				new Device(1L, "iPhone 4"),
				new Device(2L, "iPhone 4S"),
				new Device(3L, "iPhone 5"),
				new Device(4L, "Galaxy S3"),
				new Device(5L, "Galaxy S4"),
				new Device(6L, "Nexus 4"),
				new Device(7L, "Droid Razor"),
				new Device(8L, "Droid DNA"),
				new Device(9L, "HTC One"),
				new Device(10L, "iPhone 3")
		);
		deviceRepo.saveAll(deviceList);
		List<Tester> testerList = Arrays.asList(
				new Tester(1L, "Miguel", "Bautista", "US", "8/4/2013 23:57"),
				new Tester(2L, "Michael", "Lubavin", "US", "7/12/2013 13:27"),
				new Tester(3L, "Leonard", "Sutton", "GB", "7/16/2013 21:17"),
				new Tester(4L, "Taybin", "Rutkin", "US", "1/1/2013 10:57"),
				new Tester(5L, "Mingquan", "Zheng", "JP", "8/4/2013 22:07"),
				new Tester(6L, "Stanley", "Chen", "GB", "8/4/2013 21:57"),
				new Tester(7L, "Lucas", "Lowry", "JP", "7/12/2013 23:57"),
				new Tester(8L, "Sean", "Wellington", "JP", "8/5/2013 13:27"),
				new Tester(9L, "Darshini", "Thiagarajan", "GB", "8/5/2013 15:00")
		);
		testerRepo.saveAll(testerList);

		Reader reader = Files.newBufferedReader(Paths.get(
				ClassLoader.getSystemResource("bugs.csv").toURI()));

		Iterable<CSVRecord> bugDTOList = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(reader);

		for(CSVRecord dto : bugDTOList) {
			Long id = Long.parseLong(dto.get(0));
			Long deviceId = Long.parseLong(dto.get(1));
			Long testerId = Long.parseLong(dto.get(2));
			Tester tester =  testerRepo.findById(testerId).get();
			Device device = deviceRepo.findById(deviceId).get();
			Bug bug = new Bug(id, null, null);

			bug = bugRepo.save(bug);
			bug.setDevice(device);
			bug.setTester(tester);
			bugRepo.save(bug);
			deviceRepo.save(device);
			testerRepo.save(tester);
		}
	}

}
