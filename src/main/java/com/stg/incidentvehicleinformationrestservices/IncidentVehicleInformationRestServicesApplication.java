package com.stg.incidentvehicleinformationrestservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IncidentVehicleInformationRestServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidentVehicleInformationRestServicesApplication.class, args);
	}

}
