package com.push11.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@JsonSerialize
@Document(collection = "application")
public class Application extends AbstractDocument{

	@NotNull(message = "Application Name Filed should not be empty!")
	@Field("name")
	private String name;

	@NotNull
	@Reference
	private Company company;

	public Application(String id, String name, Company company) {
		super.setId(id);
		this.name = name;
		this.company = company;
	}

	public Application() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static Application newInstance(){
		return new Application();
	}
}
