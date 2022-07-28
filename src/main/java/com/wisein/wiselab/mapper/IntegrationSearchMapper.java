package com.wisein.wiselab.mapper;

import com.wisein.wiselab.dto.IntegrationSearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IntegrationSearchMapper {

	List<IntegrationSearchDTO> findIntegrationBoard(IntegrationSearchDTO dto);
	List<IntegrationSearchDTO> findIntegrationWriter(IntegrationSearchDTO dto);
}
