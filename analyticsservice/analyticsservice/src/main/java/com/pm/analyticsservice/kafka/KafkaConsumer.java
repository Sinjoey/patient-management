package com.pm.analyticsservice.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;
@Service
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient", groupId = "analytics-service")
    public void consumeEvents(byte[] event) {
        try {
            PatientEvent patientEvent=PatientEvent.parseFrom(event);
            logger.info("Patient details retrieved successfully [Patient id {} Patient name {} Patient email {}]", patientEvent.getPatientId(), patientEvent.getName(), patientEvent.getEmail());
        } catch(Exception e) {
            logger.info("Exception in deserialization of event {}", e.getMessage());
        }

    }
}
