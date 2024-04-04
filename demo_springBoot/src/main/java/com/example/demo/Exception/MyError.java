package com.example.demo.Exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MyError {

	private LocalDateTime timestamp;

	private String message;

	private String details;

}
