package application.tinkoff.config;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import application.tinkoff.assign.controller.WebServiceImpl;
import application.tinkoff.assign.service.ISearchService;


/**
 * 
 * @author Alexander Gryaznov
 *
 */
@Component
public class WsStart {
	private static final Logger log = Logger.getLogger(WsStart.class);
	@Autowired
	ISearchService search;
		
	/**
	 * 
	 * {@link #startServer()} - start webserver for getting SOAP requests
	 * @return server start status
	 */
		@Bean
		
		public String startServer() {
			Endpoint.publish(Constants.WEBSERVERURI, new WebServiceImpl(search));
			log.info("Webserver has started");
			return "OK";
		}



		

}
