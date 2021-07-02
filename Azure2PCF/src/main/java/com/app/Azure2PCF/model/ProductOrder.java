package com.app.Azure2PCF.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

public class ProductOrder {

	@Id
	private int pid;
	private String productName;
	private int qty;
	private int price;
}
