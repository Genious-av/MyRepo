package application.tinkoff.assign.controller;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import application.tinkoff.assign.dto.ResultDTO;
import application.tinkoff.assign.service.ISearchService;


/**
 * 
 * @author Alexander Gryaznov
 * 
 *@see WebServiceImpl implements IWEBSERVICE
 */
@WebService(endpointInterface = "application.tinkoff.assign.controller.IWebService")
public class WebServiceImpl implements IWebService{
	
	private static final Logger log = Logger.getLogger(WebServiceImpl.class);
	/**
	 * instanciate {@link ISearchService}
	 */
	ISearchService searchService;
	/**
	 * 
	 * @param search - pushing  bean  {@link WebServiceImpl} to current application context
	 */
	public WebServiceImpl(ISearchService search) {
		this.searchService = search;
	}

	/**
	 * implementation of function {@link WebServiceImpl} 
	 * @param number - number which is searched by the application
	 * @return - result of search in format see {@link ResultDTO}
	 */
	@Override
	public ResultDTO getNumber(String number) {
		
		
		log.info("got number from front: "+ number);
		ResultDTO result = searchService.findNumber(number);
	return result;
	}

	
	
	
}

