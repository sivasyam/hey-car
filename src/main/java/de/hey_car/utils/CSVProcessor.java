package de.hey_car.utils;

import com.opencsv.CSVReader;

import de.hey_car.dto.VehicleDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to process the CSV
 */
public class CSVProcessor {
    private static final Logger LOGGER = LogManager.getLogger(CSVProcessor.class);

    /**
     * processing CSV to publishing bean
     */
    public static List<VehicleDTO> processCSV(MultipartFile file) {
        try {
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVReader csvReader = new CSVReader(reader);

            csvReader.skip(1); // Skipping headers
            return csvReader.readAll().stream().map(p -> {
                return VehicleDTO.builder().code(p[0])
                        .make(p[1].split("/")[0])
                        .model(p[1].split("/")[1])
                        .kw(Long.parseLong(p[2]))
                        .year(Integer.parseInt(p[3]))
                        .color(p[4])
                        .price(Double.parseDouble(p[5])).build();
            }).collect(Collectors.toList());

        } catch (Exception e) {
            LOGGER.error("Unable to process the  csv request ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to process csv, exception" + e);
        }
    }
}
