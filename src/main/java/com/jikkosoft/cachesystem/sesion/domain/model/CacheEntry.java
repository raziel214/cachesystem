package com.jikkosoft.cachesystem.sesion.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CacheEntry {
	
	private String key;
    private Object value;
    private LocalDateTime expirationTime;

}
