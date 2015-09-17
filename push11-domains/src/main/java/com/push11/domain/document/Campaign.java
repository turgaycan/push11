package com.push11.domain.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "campaign")
public class Campaign {
	
	@Id
	@Field("campaign_id")
	private String campaignId;

	@Field("campaign_name")
	private String campaignName;

}
