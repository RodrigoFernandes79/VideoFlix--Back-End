package com.rodrigohf.videoFlix.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiException {
	
	private LocalDateTime timestamp;
	private String message;
	private Integer status;
	private String path;
}
