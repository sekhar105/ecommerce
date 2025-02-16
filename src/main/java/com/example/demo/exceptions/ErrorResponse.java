package com.example.demo.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse{

	private LocalDateTime localDateTime;
	private int status;
	private String error;
	private String path;
}
