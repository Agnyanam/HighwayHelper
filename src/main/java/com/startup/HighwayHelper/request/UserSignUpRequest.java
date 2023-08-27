package com.startup.HighwayHelper.request;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonRootName(value = "UserSignUpRequest")
public class UserSignUpRequest {
    
	@NotBlank(message ="The username should not be null or empty")
	@NotNull(message ="The username should not be null or empty")
	@Email(message = "The username supplied is not valid email address")
	private String userName;
	
	@NotBlank(message ="The password should not be null or empty")
	@NotNull(message ="The password should not be null or empty")
	@Size(min=8, max=50 , message= "The size of password should be more than 8")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" , message = "Password supplied is not in specified format")
	private String password;
	
	@NotBlank(message ="The Firstname should not be null or empty")
	@NotNull(message ="The Firstname should not be null or empty")
	private String firstName;
	
	private String lastName;
	
	private BigInteger mobileNumber;
	
	private String address;
	
	@NotBlank(message ="The gender should not be null or empty")
	@NotNull(message ="The Gender should not be null or empty")
	private String gender;
	
	@NotBlank(message ="The DOB should not be null or empty")
	@NotNull(message ="The DOB should not be null or empty")
	private String dateOfBirth;
	
	private List<String> roles; 
	
}
