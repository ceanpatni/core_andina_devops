package com.example.backProcesamientoStream.controller;

import com.example.backProcesamientoStream.config.PropertiesConfig;
import com.example.backProcesamientoStream.impl.GenerarImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/cotizaciones")
@RequiredArgsConstructor
public class CotizacionController {

    private final GenerarImpl generarImpl;

    // Propiedades de paginación configurables desde application.properties o variables de entorno
    private final PropertiesConfig properties;

    /**
     * Endpoint para generar manualmente el CSV de cotizaciones.
     * Página, tamaño y autoPaginate se toman desde properties/env.
     *
     * @return URL de S3 o path local donde se guardó el CSV
     */
    @GetMapping("/generar-csv")
    public ResponseEntity<String> generarCsv() {
        try {
            // Llama al backend con los parámetros configurables
            String url = generarImpl.generarCotizacionesCsv();

            String mensaje = "CSV generado correctamente: " + url +
                    " | Página inicial: " + properties.getDefaultPage() +
                    " | Tamaño página: " + properties.getDefaultSize() +
                    " | AutoPaginate: " + properties.getAutoPaginate();

            return ResponseEntity.ok(mensaje);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error generando CSV: " + e.getMessage());
        }
    }

    /**
     * Endpoint opcional para simular la subida local en lugar de S3
     */
    @GetMapping("/generar-csv-local")
    public ResponseEntity<String> generarCsvLocal() {
        try {
            byte[] csvData = generarImpl.generarCotizacionesCsv().getBytes();
            String path = generarImpl.uploadCsvToLocal(csvData, "cotizaciones_local.csv");

            String mensaje = "CSV guardado localmente: " + path +
                    " | Página inicial: " + properties.getDefaultPage() +
                    " | Tamaño página: " + properties.getDefaultSize() +
                    " | AutoPaginate: " + properties.getAutoPaginate();

            return ResponseEntity.ok(mensaje);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error guardando CSV localmente: " + e.getMessage());
        }
    }
}


