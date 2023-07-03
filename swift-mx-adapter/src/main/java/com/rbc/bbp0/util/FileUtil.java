package com.rbc.bbp0.util;

import com.rbc.bbp0.model.Data;
import com.rbc.bbp0.repository.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FileUtil {
    private final DataRepository dataRepository;

    @Autowired
    public FileUtil(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public String getContentFromFile(String filePath) throws IOException {
        //Path path = Paths.get(filePath).getFileName();

        File file=new File(filePath);
        BufferedReader reader = null;
        InputStreamReader inputStreamReader = null;
        String fileContent = null;
        try {
            inputStreamReader = new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
            reader = new BufferedReader(inputStreamReader);
            // Read the header line
            String headerLine = reader.readLine();
            String[] headers = headerLine.split("\\|");
            log.info("headers, {}",Arrays.toString(headers));
            String dataLine;
            while ((dataLine = reader.readLine()) != null) {
                String[] fields = dataLine.split("\\|");
                Data existingData=dataRepository.findByAccountNumber(Long.valueOf(fields[0]));
                // Create a Data object with the extracted fields
                if(existingData!=null){
                    existingData.setFrcNumber(Long.valueOf(fields[1]));
                    existingData.setName(fields[2]);
                    existingData.setStatus(fields[3]);
                    dataRepository.save(existingData);
                }else{
                    Data data = new Data();
                    data.setAccountNumber(Long.valueOf(fields[0]));
                    data.setFrcNumber(Long.valueOf(fields[1]));
                    data.setName(fields[2]);
                    data.setStatus(fields[3]);
                    dataRepository.save(data);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            fileContent = Objects.requireNonNull(reader).lines().collect(Collectors.joining());
            Objects.requireNonNull(inputStreamReader).close();
        }
        return fileContent;
    }
}
