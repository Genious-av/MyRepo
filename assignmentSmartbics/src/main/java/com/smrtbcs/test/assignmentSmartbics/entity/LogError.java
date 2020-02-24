package com.smrtbcs.test.assignmentSmartbics.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogError {
	LocalDateTime theTime;
	String typeOfError;
	
}
