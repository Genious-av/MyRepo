package telran.m2m.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import telran.m2m.configuration.RestApi;
import telran.m2m.dto.Sensor;
import telran.m2m.service.IService;

@RestController
public class BackOfficeController {
	@Autowired
	IService service;
	
	@GetMapping(RestApi.getValuesForPeriod)
	public List<Sensor> getValuesForPeriod(@RequestParam int id, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd-H-m-s") LocalDateTime from,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd-H-m-s") LocalDateTime to ){
		return service.getValuesForPeriod(id, from, to);
	}
	
	
	@GetMapping(RestApi.getMinMaxValuesForPeriod)
	public List<Sensor> getMinMaxValuesForPeriod(@RequestParam int id,@RequestParam int minValue,@RequestParam int maxValue, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd:H:m:s") LocalDateTime from,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd:H:m:s") LocalDateTime to ){
		return service.getMinMaxValuesForPeriod(id, from, to, minValue, maxValue);
	}
	
	@GetMapping(RestApi.getValuesGreaterThen)
	public List<Sensor> getValuesGreaterThen(@RequestParam int id,@RequestParam int minValue){
		return service.getValuesGreaterThen(id, minValue);
	}
}
