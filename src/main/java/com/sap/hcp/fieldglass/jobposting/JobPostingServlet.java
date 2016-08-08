package com.sap.hcp.fieldglass.jobposting;


import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonParseException;

@WebServlet("/post")
public class JobPostingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String INDEX_HTML = "index.html";
	
	private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";
       
    public JobPostingServlet() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(INDEX_HTML).forward(request, response);
	}
	
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jobPostingJson = IOUtils.toString(request.getInputStream());
		
		try{
			JobPosting jobPosting = JobPosting.parseFromJson(jobPostingJson);
			jobPosting.setUniqueExternalId();
		
			SOAPMessage soapResponse = JobPostingSOAPClient.submitToSOAPServer(jobPosting);
			String message;
			if (soapResponse.getSOAPBody().hasFault()){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				message = soapResponse.getSOAPBody().getFault().getTextContent();
				
				//message = message.replaceAll(".*throwable", "");
			} else {
				message = soapResponse.getSOAPBody().getTextContent();
			}
			response.getWriter().append(message);
			
		} catch (JsonParseException | IllegalArgumentException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().append(e.getMessage());
		} catch (ServerException | SOAPException | ExceptionInInitializerError e){
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().append(INTERNAL_SERVER_ERROR_MESSAGE);
		}
	}
}










