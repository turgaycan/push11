package com.push11.domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonSerialize
@Document(collection = "applications")
public class Application extends AbstractDocument{

	@Field("app_id")
	private String appId;

	@Field("app_name")
	private String appName;

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
}
