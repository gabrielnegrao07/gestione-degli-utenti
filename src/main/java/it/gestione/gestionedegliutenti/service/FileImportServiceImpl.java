package it.gestione.gestionedegliutenti.service;

import it.gestione.gestionedegliutenti.model.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileImportServiceImpl implements FileImportService {

    UserService userService;

    public static final String TYPE = "text/csv";

    @Override
    public void importCsvFile(MultipartFile file) {
        if (validateFormat(file)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                reader.lines()
                        .skip(1)
                        .filter(line -> line.length() > 0)
                        .map(this::mapToUserEntity)
                        .forEach(userService::save);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    private UserEntity mapToUserEntity(String line) {
        String[] fields = line.split(";");
        String name = null;
        if (isExistFieldPosition(fields.length, 0)) {
            name = fields[0].trim();
        }
        String surname = null;
        if (isExistFieldPosition(fields.length, 1)) {
            surname = fields[1].trim();
        }
        String mail = null;
        if (isExistFieldPosition(fields.length, 2)) {
            mail = fields[2].trim();
        }
        String address = null;
        if (isExistFieldPosition(fields.length, 3)) {
            address = fields[3].trim();
        }
        return UserEntity.builder()
                .name(name)
                .surname(surname)
                .mail(mail)
                .address(address)
                .build();
    }

    public static boolean validateFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    private Boolean isExistFieldPosition(Integer tamanho, Integer posicao) {
        return tamanho > posicao;
    }
}
