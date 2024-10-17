package com.jikkosoft.cachesystem.infraestructure.controller;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.in.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cache")
@Tag(name = "CacheController", description = "API para gestionar el sistema de caché distribuido")
public class CacheController {

    private final SaveCacheEntryUseCase saveCacheEntryUseCase;
    private final FindByIdCacheEntryUseCase findByIdCacheEntryUseCase;
    private final FetchAllCacheEntryUseCase fetchAllCacheEntryUseCase;
    private final UpdateCacheEntryUseCase updateCacheEntryUseCase;
    private final DeleteCacheEntryUseCase deleteCacheEntryUseCase;

    public CacheController(SaveCacheEntryUseCase saveCacheEntryUseCase,
    					   FindByIdCacheEntryUseCase findByIdCacheEntryUseCase,
    					   FetchAllCacheEntryUseCase fetchAllCacheEntryUseCase,
                           UpdateCacheEntryUseCase updateCacheEntryUseCase,
                           DeleteCacheEntryUseCase deleteCacheEntryUseCase) {
        this.saveCacheEntryUseCase = saveCacheEntryUseCase;
        this.findByIdCacheEntryUseCase = findByIdCacheEntryUseCase;
        this.fetchAllCacheEntryUseCase = fetchAllCacheEntryUseCase;
        this.updateCacheEntryUseCase = updateCacheEntryUseCase;
        this.deleteCacheEntryUseCase = deleteCacheEntryUseCase;
    }

    @Operation(summary = "Guarda una entrada en el caché")
    @PostMapping
    public ResponseEntity<Void> saveCacheEntry(@RequestBody CacheEntry cacheEntry) {
        saveCacheEntryUseCase.save(cacheEntry);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Obtiene una entrada del caché por clave")
    @GetMapping("/{key}")
    public ResponseEntity<CacheEntry> getCacheEntryByKey(@PathVariable String key) {
        CacheEntry cacheEntry = findByIdCacheEntryUseCase.findById(key);
        if (cacheEntry != null) {
            return ResponseEntity.ok(cacheEntry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtiene todas las entradas del caché")
    @GetMapping
    public ResponseEntity<List<CacheEntry>> getAllCacheEntries() {
        List<CacheEntry> cacheEntries = fetchAllCacheEntryUseCase.fetchAllCacheEntry();
        return ResponseEntity.ok(cacheEntries);
    }

    @Operation(summary = "Actualiza una entrada en el caché")
    @PutMapping("/{key}")
    public ResponseEntity<Void> updateCacheEntry(@PathVariable String key, @RequestBody CacheEntry cacheEntry) {
        cacheEntry.setKey(key); // Aseguramos que la clave sea la misma
        updateCacheEntryUseCase.updateCacheEntry(cacheEntry);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Elimina una entrada del caché por clave")
    @DeleteMapping("/{key}")
    public ResponseEntity<Void> deleteCacheEntry(@PathVariable String key) {
        deleteCacheEntryUseCase.delete(key);
        return ResponseEntity.ok().build();
    }
}

