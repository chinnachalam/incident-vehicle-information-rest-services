package com.stg.incidentvehicleinformationrestservices.repository;

import com.stg.incidentvehicleinformationrestservices.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
