package application.tinkoff.assign.controller;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import application.tinkoff.assign.dto.ResultDTO;
/** interface which describe SOAP web-service including all his methods
 * @see WebServiceImpl
 * */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IWebService {
	/**
	 * 
	 * @param number - number which is searched by the application
	 * @return - result of search in format see {@link ResultDTO}
	 */
	    @WebMethod
	    public ResultDTO getNumber(String number);
}

