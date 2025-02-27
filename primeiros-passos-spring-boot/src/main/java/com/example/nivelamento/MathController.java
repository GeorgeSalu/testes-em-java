package com.example.nivelamento;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.nivelamento.converter.NumberConverter;
import com.example.nivelamento.exceptions.UnsuportedMathOperationException;

@RestController
public class MathController {

	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("por favor entre com um valor numerico");
		}
		
		return NumberConverter.convertToDouble(numberOne) + NumberConverter.convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double subtraction(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("por favor entre com um valor numerico");
		}
		
		return NumberConverter.convertToDouble(numberOne) - NumberConverter.convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double multiplication(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("por favor entre com um valor numerico");
		}
		
		return NumberConverter.convertToDouble(numberOne) * NumberConverter.convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double division(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("por favor entre com um valor numerico");
		}
		
		return NumberConverter.convertToDouble(numberOne) / NumberConverter.convertToDouble(numberTwo);
	}
	
	@RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mean(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("por favor entre com um valor numerico");
		}
		
		return (NumberConverter.convertToDouble(numberOne) + NumberConverter.convertToDouble(numberTwo)) / 2;
	}
	
	@RequestMapping(value = "/squareRoot/{number}", method = RequestMethod.GET)
	public Double squareRoot(
			@PathVariable(value = "number") String number) throws Exception {
		
		if (!NumberConverter.isNumeric(number)) {
			throw new UnsuportedMathOperationException("por favor entre com um valor numerico");
		}
		
		return Math.sqrt(NumberConverter.convertToDouble(number));
	}
	
}
