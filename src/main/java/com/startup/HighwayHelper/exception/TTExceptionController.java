package com.startup.HighwayHelper.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.startup.HighwayHelper.response.TTErrorResponse;
import com.startup.HighwayHelper.response.UserSignUpResponse;
import com.startup.HighwayHelper.utils.HighwayHelperConstants;

@ControllerAdvice
public class TTExceptionController {

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserSignUpException.class)
	@ResponseBody
	public UserSignUpResponse userSignUpException(UserSignUpException ex) {
		return new UserSignUpResponse(ex.getCode(), ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(HHInternalException.class)
	@ResponseBody
	public TTErrorResponse internalError(HHInternalException ex) {
		return new TTErrorResponse(ex.getCode(), ex.getMessage());
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public UserSignUpResponse argumentsInvalidException(MethodArgumentNotValidException ex) {
		return new UserSignUpResponse(HighwayHelperConstants.HH_DETAILS_VALIDATIONS_CODE , ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
	}
	
}
 