package it.gestione.gestionedegliutenti.controller;

import it.gestione.gestionedegliutenti.service.FileImportService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api-utenti")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileImportController {

    FileImportService service;

    @PostMapping("/import")
    public ResponseEntity<Void> importCsvFile(MultipartFile file) {
        service.importCsvFile(file);
        return ResponseEntity.noContent().build();
    }
}
