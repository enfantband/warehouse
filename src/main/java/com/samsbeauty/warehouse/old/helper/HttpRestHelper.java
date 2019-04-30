package com.samsbeauty.warehouse.old.helper;

import org.springframework.beans.factory.annotation.Value;

public abstract class HttpRestHelper {
	@Value("${samsbeauty.api.url}")
	protected String apiUrl;
	

	@Value("${samsbeauty.api.key.common}")
	protected String apiCommonKey;
	
	
}
