package de.hey_car.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.hey_car.dto.VehicleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Class to process the CSV
 */
public class CSVProcessor {
    private static final Logger LOGGER = LogManager.getLogger(CSVProcessor.class);

    /**
     * processing CSV to publishing bean
     *
     * @param file
     * @return
     */
    public static CsvToBean<VehicleDTO> processCSV(MultipartFile file) {
        try {
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

            return new CsvToBeanBuilder(reader)
                    .withType(VehicleDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(',')
                    .build();
        } catch (Exception e) {
            LOGGER.error("Unable to process the  csv request ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to process csv, exception" + e);
        }
    }
}
