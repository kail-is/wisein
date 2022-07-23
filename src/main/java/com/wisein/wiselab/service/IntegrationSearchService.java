package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.IntegrationSearchDTO;

import java.util.List;
import java.util.Map;

public interface IntegrationSearchService {

	Map<String, List<IntegrationSearchDTO>> findIntegrationBoard(IntegrationSearchDTO dto);
	Map<String,List<IntegrationSearchDTO>> findIntegrationWriter(IntegrationSearchDTO dto);

}
