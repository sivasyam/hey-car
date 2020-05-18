package de.hey_car;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.hey_car.dto.VehicleDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class VehicleIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUploadVehicleCSV() throws Exception {
        MockMultipartFile csvfile = new MockMultipartFile("file", "test.csv", "multipart/form-data;", "1,mercedes/a 180,123,2014,black,123950".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/vehicle/upload_csv/1")
                .file(csvfile)
                .param("some-random", "4"))
                .andExpect(status().is(400));
    }

    @Test
    public void testSaveVehicle() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        vehicleDTOList.add(VehicleDTO.builder().model("c class").make("benz").price(12234.00).kw(123L).year(2020).color("White").code("1a").build());
        String requestJson = ow.writeValueAsString(vehicleDTOList);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/vehicle/vehicle_listings/1")
                .content(requestJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testSaveVehicleFailureCase() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        String requestJson = ow.writeValueAsString(vehicleDTOList);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/vehicle/vehicle_listings/1")
                .content(requestJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
    }
}
