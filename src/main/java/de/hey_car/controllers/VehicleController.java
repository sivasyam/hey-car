package de.hey_car.controllers;

import com.querydsl.core.BooleanBuilder;

import de.hey_car.dto.VehicleDTO;
import de.hey_car.entity.QVehicleEntity;
import de.hey_car.services.VehicleService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

/**
 * Vehicle controller responsible for publishing vehicle (upload CSV or using JSON) and search published listing
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicle")
public class VehicleController {
    private static final Logger LOGGER = LogManager.getLogger(VehicleController.class);

    @Autowired
    private VehicleService vehicleService;

    /**
     * Method to upload publishing CSV file
     */
    @PostMapping(value = "/upload_csv/{dealerId}")
    public ResponseEntity<String> publishCSV(@PathVariable Long dealerId, @RequestParam("file") MultipartFile file) {
        LOGGER.info("Processing upload CSV request for the dealer {}", dealerId);
        if (file.isEmpty()) {
            LOGGER.warn("CSV file is empty for the dealer {}", dealerId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please upload the file");
        }
        vehicleService.uploadCSV(file, dealerId);
        return ResponseEntity.ok().body("List published successfully ");
    }

    /**
     * Method to publish the listing from JSON
     */
    @PostMapping("/vehicle_listings/{dealerId}")
    public ResponseEntity<String> publishJSON(@PathVariable Long dealerId, @RequestBody(required = true) @Valid List<VehicleDTO> vehicleList) {
        LOGGER.info("Processing publish list for the dealer {}", dealerId);
        if (vehicleList.size() == 0) {
            return ResponseEntity.badRequest().body("Invalid request provided");
        }
        vehicleService.saveListing(vehicleList, dealerId);
        return ResponseEntity.ok().body("List published successfully");
    }

    /**
     * Method to search the publishings using different params like Model, Make, year etc
     */
    @GetMapping("/search")
    public ResponseEntity<List<VehicleDTO>> searchPublishing(@RequestParam(required = false) Map<String, String> _filter) {
        LOGGER.info("Searching vehicle list");
        return ResponseEntity.ok().body(vehicleService.searchPublishing(buildBooleanBuilder(_filter)));
    }

    private BooleanBuilder buildBooleanBuilder(Map<String, String> _filter) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (_filter.containsKey("model")) {
            booleanBuilder.and(QVehicleEntity.vehicle.modle.equalsIgnoreCase(_filter.get("model")));
        }
        if (_filter.containsKey("make")) {
            booleanBuilder.and(QVehicleEntity.vehicle.make.equalsIgnoreCase(_filter.get("make")));
        }
        if (_filter.containsKey("color")) {
            booleanBuilder.and(QVehicleEntity.vehicle.color.equalsIgnoreCase(_filter.get("color")));
        }
        if (_filter.containsKey("color")) {
            booleanBuilder.and(QVehicleEntity.vehicle.year.eq(Long.parseLong(_filter.get("year"))));
        }
        return booleanBuilder;
    }
}
