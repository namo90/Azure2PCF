package com.app.Azure2PCF.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class orderResponseDto {
	

    private String name;
    private String productName;
    private int price;  
    public orderResponseDto(String name, String productName) {
        this.name = name;
        this.productName = productName;
    }

    
    

}
