package com.maruti.springbootmongo.collections;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
	@Field("temp_address")
	private String tempAddress;
	@Field("permanant_address")
	private String PermanantAddress;

	private String city;
}
