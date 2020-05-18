package de.hey_car.services;

import com.querydsl.core.BooleanBuilder;

import de.hey_car.dto.VehicleDTO;
import de.hey_car.entity.VehicleEntity;
import de.hey_car.repository.VehicleRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import static de.hey_car.utils.CSVProcessor.processCSV;

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleService {

    private static final Logger LOGGER = LogManager.getLogger(VehicleService.class);

    private final VehicleRepository vehicleRepository;

    /**
     * Method to process the data from CSV file
     */
    public String uploadCSV(MultipartFile file, Long dealerId) {
        LOGGER.info("Processing data from csv for the dealer {}", dealerId);

        List list = vehicleRepository.saveAll(buildVehicleEntity(processCSV(file), dealerId));
        if (list.size() > 0) {
            LOGGER.info("published successfully ");
            return "Published Successfully";
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error Occurred while publishing");
        }
    }

    /**
     * Method to save publishing
     */
    public void saveListing(List<VehicleDTO> vehicleList, Long dealerId) {
        try {
            LOGGER.info("Publishing vehicle list from JSON for the dealer {}", dealerId);
            vehicleRepository.saveAll(buildVehicleEntity(vehicleList, dealerId));
        } catch (Exception e) {
            LOGGER.error("Unable to process the request ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to publish the listing" + e.getMessage());
        }
    }

    /**
     * Method to search publishing
     */
    public List<VehicleDTO> searchPublishing(BooleanBuilder booleanBuilder) {
        return (booleanBuilder.getValue() != null) ?
                buildVehicleResponse((List<VehicleEntity>) vehicleRepository.findAll(booleanBuilder.getValue()))
                : buildVehicleResponse(vehicleRepository.findAll());
    }

    /**
     * Method to build vehicle entity
     */
    private List<VehicleEntity> buildVehicleEntity(List<VehicleDTO> vehicleList, Long dealerId) {
        return vehicleList.stream()
                .map(p -> {
                    if (p.getMake() != null && p.getMake().contains("/")) {
                        String[] makeAndModel = p.getMake().split("/");
                        p.setMake(makeAndModel[0]);
                        p.setModel(makeAndModel[1]);
                    }
                    return buildVehicleEntity(p, dealerId);
                }).collect(Collectors.toList());
    }

    /**
     * Method to build vehicle entity
     */
    private VehicleEntity buildVehicleEntity(VehicleDTO vehicleDTO, Long dealerId) {
        return VehicleEntity.builder()
                .code(vehicleDTO.getCode())
                .color(vehicleDTO.getColor())
                .kw(vehicleDTO.getKw())
                .make(vehicleDTO.getMake())
                .model(vehicleDTO.getModel())
                .price(vehicleDTO.getPrice())
                .year(vehicleDTO.getYear())
                .dealerId(dealerId)
                .build();
    }

    /**
     *
     */
    private List<VehicleDTO> buildVehicleResponse(List<VehicleEntity> vehicleEntityList) {
        if (vehicleEntityList.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No result found");
        return vehicleEntityList.stream().map(p -> {
            return VehicleDTO.builder()
                    .code(p.getCode())
                    .color(p.getColor())
                    .kw(p.getKw())
                    .year(p.getYear())
                    .price(p.getPrice())
                    .make(p.getMake())
                    .model(p.getModel())
                    .build();
        }).collect(Collectors.toList());
    }
}
