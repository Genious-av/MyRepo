package application.tinkoff.assign.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.tinkoff.assign.dto.ResultDTO;
import application.tinkoff.assign.entity.ResultEntity;
import application.tinkoff.assign.repository.ResultRepository;
import application.tinkoff.config.CodeResults;
import application.tinkoff.finder.FindInFiles;

/**
 * 
 * @author Alexander Gryaznov
 *
 */

/**
 * 
 * {@link SearchService} class which implements {@link ISearchService}
 *
 */
@Service
public class SearchService implements ISearchService{
	private static final Logger log = Logger.getLogger(SearchService.class);
	
	@Autowired
	ResultRepository resultRepository;
	
	/**
	 * @param number - searched number
	 * {@link #findNumber(int)} - function for start search of number: instantiate {@link FindInFiles#startSearch(int)},
	 * forming {@link ResultDTO } and {@link ResultEntity}
	 * @return {@link ResultDTO}
	 */
	@Override
	public ResultDTO findNumber(String number) {
		int num=0;
		try {
			num=Integer.parseInt(number);
		} catch (NumberFormatException e) {
			ResultDTO resultErr = new ResultDTO(CodeResults.resultEr.getResult(), new ArrayList<String>(), e.getMessage());
			resultRepository.save(new ResultEntity(resultErr.getCode(),num, resultErr.getFileNames().toString(),resultErr.getError()));
			log.error("Wrong NumberFormat " +e.getMessage());
			return resultErr;
		}
				
		FindInFiles findInFiles=new FindInFiles();
		ResultDTO resultDTO;
		log.info("Search started for number " +num);
		resultDTO = findInFiles.startSearch(num);
		ResultEntity resultEntity = new ResultEntity(resultDTO.getCode(),num, resultDTO.getFileNames().toString(),resultDTO.getError());
		log.info("Search ended for number " +number);
		resultRepository.save(resultEntity);
		log.info("Result of search saved to db");
		return resultDTO;
	}

}
