package com.wisein.wiselab.service;

import com.sun.mail.imap.protocol.Item;
import com.wisein.wiselab.mapper.IntegrationSearchMapper;
import com.wisein.wiselab.dto.IntegrationSearchDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IntegrationSearchServiceImpl implements IntegrationSearchService {
	private static final Map<String,List<IntegrationSearchDTO>> INTERGRATIONSEARCHDTOMAP = new HashMap();
	private final IntegrationSearchMapper integrationSearchMapper;

	@Override
	public Map<String,List<IntegrationSearchDTO>> findIntegrationBoard(IntegrationSearchDTO dto) {

		List<IntegrationSearchDTO> resultList = integrationSearchMapper.findIntegrationBoard(dto);

		Map<String, List<IntegrationSearchDTO>> resultMap = resultList.stream()
				.collect(Collectors.groupingBy( IntegrationSearchDTO::getBoardType));

		return resultMap == null ? INTERGRATIONSEARCHDTOMAP : resultMap;
	}

	@Override
	public Map<String,List<IntegrationSearchDTO>> findIntegrationWriter(IntegrationSearchDTO dto) {
		List<IntegrationSearchDTO> resultList = integrationSearchMapper.findIntegrationWriter(dto);

		Map<String, List<IntegrationSearchDTO>> resultMap = resultList.stream()
				.collect(Collectors.groupingBy( IntegrationSearchDTO::getBoardType));

		return resultMap == null ? INTERGRATIONSEARCHDTOMAP : resultMap;
	}
}
