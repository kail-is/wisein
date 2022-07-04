package com.wisein.wiselab.controller;

import com.wisein.wiselab.dto.IntegrationSearchDTO;
import com.wisein.wiselab.service.IntegrationSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/integration")
public class IntegratedController {

    private final IntegrationSearchService integrationSearchService;
    private static final int INTEGRATION_SEARCH_RECORDS_PER_SCROLL_PAGE = 35;

    @GetMapping(value = "/board")
    public String integrationBoard (IntegrationSearchDTO integrationSearchDTO, Model model) throws Exception {
        model.addAttribute("viewRecordsPerPage",INTEGRATION_SEARCH_RECORDS_PER_SCROLL_PAGE);
        model.addAttribute("searchType",integrationSearchDTO.getSearchType());
        model.addAttribute("keyword",integrationSearchDTO.getKeyword());
        return "cmn/totalSearch";
    }

    @ResponseBody
    @GetMapping(value = "/board-data")
    public Map<String,List<IntegrationSearchDTO>> integrationBoardData (IntegrationSearchDTO integrationSearchDTO) throws Exception {
        integrationSearchDTO.setViewRecordsPerPage(INTEGRATION_SEARCH_RECORDS_PER_SCROLL_PAGE);
        return integrationSearchService.findIntegrationBoard(integrationSearchDTO);
    }

    @GetMapping(value = "/writer")
    public String integrationWriter(IntegrationSearchDTO integrationSearchDTO, Model model) throws Exception {
        model.addAttribute("viewRecordsPerPage",INTEGRATION_SEARCH_RECORDS_PER_SCROLL_PAGE);
        return "cmn/totalSearch";
    }

    @ResponseBody
    @GetMapping(value = "/writer-data")
    public Map<String,List<IntegrationSearchDTO>> integrationWriterData(IntegrationSearchDTO integrationSearchDTO) throws Exception {
        integrationSearchDTO.setViewRecordsPerPage(INTEGRATION_SEARCH_RECORDS_PER_SCROLL_PAGE);
        return integrationSearchService.findIntegrationWriter(integrationSearchDTO);
    }
}
