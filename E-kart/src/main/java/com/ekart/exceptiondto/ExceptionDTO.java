package com.ekart.exceptiondto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ExceptionDTO {
	
	private LocalDateTime dateAndTime;
	
	private String message;
	
	private String desc;
	
}
