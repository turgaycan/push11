package com.push11.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@JsonSerialize
@Document(collection = "applications")
public class Application extends AbstractDocument{

	@NotNull(message = "Application ID Filed should not be empty!")
	@NotEmpty
	@Field("app_id")
	private String appId;

	@NotNull(message = "Application Name Filed should not be empty!")
	@Field("app_name")
	private String appName;

	@NotNull
	@Reference
	private Company company;

	public Application(String appId, String appName, Company company) {
		this.appId = appId;
		this.appName = appName;
		this.company = company;
	}

	public Application() {
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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
