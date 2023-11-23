package com.robert.yweather.service;

import com.robert.yweather.model.Weather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private static final String FILE_PATH = "output.csv";

    public void writeToFile(Flux<Weather> weatherFlux) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            // Write data to the CSV file
            weatherFlux
                    .doOnNext(weather -> {
                        try {
                            bufferedWriter.write(String.format("%s,%s,%s\n", weather.getName(), weather.getTemperature(), weather.getWind()));
                        } catch (IOException e) {
                            log.error(e.getMessage());
                        }
                    })
                    .blockLast(); // block to wait for the completion of the Flux

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
