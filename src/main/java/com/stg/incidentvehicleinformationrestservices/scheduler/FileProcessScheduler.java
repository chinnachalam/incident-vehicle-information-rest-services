package com.stg.incidentvehicleinformationrestservices.scheduler;

import com.stg.incidentvehicleinformationrestservices.model.Incident;
import com.stg.incidentvehicleinformationrestservices.repository.IncidentRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

@Component
public class FileProcessScheduler {

    @Autowired
    private IncidentRepository incidentRepository;

    static final String FILE_NAME = "Test Process_14122021.csv"; // --> admin/super_admin --> todo --> in_process  --> completed

    @Scheduled(cron = "0 0/1 * * * ?")
    public void processFile() {
        System.out.println("**** STARTED PROCESS FILE *****");

        // read from todo --> in_process

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME)));
             CSVParser csvParser = new CSVParser(bufferedReader,
                 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            Set<Incident> incidents = new HashSet<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Incident incident = new Incident();
                incident.setLogNumber(csvRecord.get("LOG_NO"));
                incident.setDcPrefix(csvRecord.get("DC_PREFIX"));
                incident.setDcName(csvRecord.get("DC_NAME"));
                incident.setDcFault(csvRecord.get("DC_FAULT"));
                incident.setDcAvail(csvRecord.get("DC_AVAIL"));
                incident.setLocFrnt(csvRecord.get("LOC_FRNT"));
                incident.setLocRear(csvRecord.get("LOC_REAR"));
                incident.setLocLeft(csvRecord.get("LOC_LEFT"));
                incident.setLocRigh(csvRecord.get("LOC_RIGH"));
                incident.setPartRec(csvRecord.get("PART_REC"));
                incident.setPartmemo(csvRecord.get("PARTMEMO"));
                incident.setCodedBy(csvRecord.get("CODED_BY"));
                incident.setFieldInv(csvRecord.get("FIELD_INV"));
                incidents.add(incident);
            }

            incidentRepository.saveAll(incidents);

            System.out.println("**** COMPLETED PROCESS FILE *****");

            // move file to completed
        } catch (Exception e) {
            System.out.println("fail to parse CSV file: " + e.getMessage());
        }
    }
}
