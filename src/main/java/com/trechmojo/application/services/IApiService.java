package com.trechmojo.services;

import java.util.Map;

public interface IApiService {
	public Map<String, Object> getResult();

	public Map<String, Object> getError(String user);
}
