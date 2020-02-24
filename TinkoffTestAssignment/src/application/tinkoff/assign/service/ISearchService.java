package application.tinkoff.assign.service;

import org.springframework.stereotype.Component;

import application.tinkoff.assign.dto.ResultDTO;
@Component
public interface ISearchService {
	ResultDTO findNumber (String number);
}
