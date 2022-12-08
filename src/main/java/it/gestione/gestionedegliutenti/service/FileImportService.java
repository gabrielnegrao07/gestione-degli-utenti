package it.gestione.gestionedegliutenti.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileImportService {

    void importCsvFile(MultipartFile file);
}
