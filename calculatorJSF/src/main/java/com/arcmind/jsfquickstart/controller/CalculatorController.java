package com.arcmind.jsfquickstart.controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import com.arcmind.jsfquickstart.model.Calculator;
import com.springsource.citydistance.CityDistance;
import com.springsource.citydistance.CityDistanceService;
import com.springsource.citydistance.GetCityDistanceRequest;
import com.springsource.citydistance.GetCityDistanceResponse;
import java.net.URL;
import net.webservicex.Currency;
import net.webservicex.CurrencyConvertor;
import net.webservicex.CurrencyConvertorSoap;

public class CalculatorController {

	private Calculator calculator;	
	private UIInput firstNumberInput;
	private UIInput secondNumberInput;
		

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

	public Calculator getCalculator() {
		return calculator;
	}

	public String add() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			calculator.add();
			CurrencyConvertor service = new CurrencyConvertor();
			CurrencyConvertorSoap port = service.getPort(CurrencyConvertorSoap.class);
			
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Added successfully "+port.conversionRate(Currency.GBP, Currency.AUD), null));
			

		} catch (Exception ex) {
			facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
		}
		return "results";
	}

	public String multiply() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			calculator.multiply();
			CityDistanceService service = new CityDistanceService();
			
			CityDistance port = service.getCityDistanceSoap11();
			
			GetCityDistanceRequest distanceRequest	= new GetCityDistanceRequest();
			distanceRequest.setCity1(calculator.getFirstNumber()+"");
			distanceRequest.setCity2(calculator.getSecondNumber()+"");
			GetCityDistanceResponse response = port.getCityDistance(distanceRequest);
			
			facesContext.addMessage(null, new FacesMessage(		
					FacesMessage.SEVERITY_INFO, "Multiplied successfully "+response.getStatus(), null));

		} catch (Exception ex) {
			facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
		}
		return "results";
	}

	public String divide() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {
			calculator.divide();
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Divided successfully", null));

		} catch (Exception ex) {
			if (ex instanceof ArithmeticException) {
				secondNumberInput.setValue(Integer.valueOf(1));
			}
			facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
		}
		return "results";
	}

	public String clear() {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		try {

			
		        calculator.clear();
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Results cleared", null));

		} catch (Exception ex) {
			facesContext.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
		}
		return null;
	}

	public String getFirstNumberStyleClass() {
		if (firstNumberInput == null || firstNumberInput.isValid()) {
			return "labelClass";
		} else {
			return "errorClass";
		}		
	}

	public String getSecondNumberStyleClass() {
		if (secondNumberInput == null || secondNumberInput.isValid()) {
			return "labelClass";
		} else {
			return "errorClass";
		}		
	}

	public UIInput getFirstNumberInput() {
		return firstNumberInput;
	}

	public void setFirstNumberInput(UIInput firstNumberInput) {
		this.firstNumberInput = firstNumberInput;
	}

	public UIInput getSecondNumberInput() {
		return secondNumberInput;
	}

	public void setSecondNumberInput(UIInput secondNumberInput) {
		this.secondNumberInput = secondNumberInput;
	}

	
}
