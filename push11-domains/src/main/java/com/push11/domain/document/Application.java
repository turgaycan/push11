package com.push11.domain.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@Document(collection = "application")
public class Application extends AbstractDocument{

	private static final long serialVersionUID = 2508593190436931705L;

	@Id
	@Field(value = "application_id")
	private String applicationId;

	@NotNull(message = "Application Name Filed should not be empty!")
	@Field("name")
	private String name;

	@NotNull
	@Reference
	private Company company;

	public Application() {
	}

	public Application(String applicationId) {
		this.applicationId = applicationId;
	}

	public Application(String applicationId, String name, Company company) {
		this.applicationId = applicationId;
		this.name = name;
		this.company = company;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
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
