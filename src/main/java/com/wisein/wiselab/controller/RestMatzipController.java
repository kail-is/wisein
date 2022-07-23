package com.wisein.wiselab.controller;

import com.wisein.wiselab.config.JsonInstance;
import com.wisein.wiselab.dto.CompanyDTO;
import com.wisein.wiselab.service.FoodListService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class RestMatzipController {

    @Autowired
    FoodListService service;

    JSONObject jObject;
    JSONParser jParser;
    JSONArray jArray;

    private static String GEOCODE_USER_INFO="KakaoAK b47531135c6a0118871c7c53f47e27be";

    public RestMatzipController() {
        this.jObject = JsonInstance.getjObjectInstance();
        this.jParser = JsonInstance.getJsonParserInstatnce();
        this.jArray = JsonInstance.getJsonArrayInstance();
    }

    @GetMapping(value="/companyList")
    public Map<String, List<CompanyDTO>> companyList(CompanyDTO companyDTO, Model model) {
        ModelAndView mv = new ModelAndView();
        List<CompanyDTO> siteList = service.companyList();
        List<CompanyDTO> company = new ArrayList<>();

        Map<String, List<CompanyDTO>> map = new HashMap<>();

        for (int i=0; i<siteList.size(); i++) {
            companyDTO = new CompanyDTO();

            try {
                jObject = (JSONObject) jParser.parse(siteList.get(i).getCompanydata());
                jArray = (JSONArray) jObject.get("documents");
                jObject = (JSONObject) jArray.get(0);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            List<CompanyDTO> siteCountList = service.matzipCount(siteList.get(i).getLocation());

            companyDTO.setId(siteList.get(i).getId());
            companyDTO.setLocation(siteList.get(i).getLocation());
            companyDTO.setMatzipCount(siteCountList.get(0).getMatzipCount());
            companyDTO.setCompanyName((String) jObject.get("place_name"));
            companyDTO.setCompanyLoc((String) jObject.get("address_name"));

            company.add(companyDTO);
        }

        map.put("company", company);
        return map;

    }

    @GetMapping(value="/placeMatzip")
    public Map<String, List<CompanyDTO>> companyList(CompanyDTO companyDTO,Model model,
                                                     @RequestParam("place") String place) {

        List<CompanyDTO> company = new ArrayList<>();

        List<CompanyDTO> matzipList = service.matzipList(place);

        Map<String, List<CompanyDTO>> map = new HashMap<>();

        for (int i=0; i<matzipList.size(); i++) {
            companyDTO = new CompanyDTO();

            List<CompanyDTO> recmMatzipList = service.recmMatzipCount(matzipList.get(i).getId());

            try {
                jObject = (JSONObject) jParser.parse(matzipList.get(i).getCompanydata());
                jArray = (JSONArray) jObject.get("documents");
                jObject = (JSONObject) jArray.get(0);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            companyDTO.setId(matzipList.get(i).getId());
            companyDTO.setLocation(matzipList.get(i).getLocation());
            companyDTO.setCompanyName((String) jObject.get("place_name"));
            companyDTO.setCompanyLoc((String) jObject.get("address_name"));
            companyDTO.setMatzipCount(recmMatzipList.get(0).getMatzipCount());

            company.add(companyDTO);
        }

        map.put("company", company);

        return map;
    }

    @GetMapping(value="/lateChange")
    public static Map findGeoPoint(@RequestParam("local") String location) {
        URL obj;
        Map foodLocalMap = new HashMap<String, Object>();

        try {
            String address = URLEncoder.encode(location , "UTF-8");

            String GEOCODE_URL = "http://dapi.kakao.com/v2/local/search/address.json?query=";
            obj = new URL(GEOCODE_URL + address);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", GEOCODE_USER_INFO);
            con.setRequestProperty("content-type", "application/json");
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setDefaultUseCaches(false);

            Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            foodLocalMap.put("local", response.toString());

            return foodLocalMap;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value="/selectCompany")
    public Map<String, List<CompanyDTO>> selectCompany(@RequestParam("place") String location,
                                                       CompanyDTO companyDTO) {
        Map<String, List<CompanyDTO>> selectCompanyMap = new HashMap<>();

        List<CompanyDTO> companyList = service.selectCompany(location);
        List<CompanyDTO> selectList = new ArrayList<>();

        for (int i=0; i<companyList.size(); i++) {
            companyDTO = new CompanyDTO();

            try {
                jObject = (JSONObject) jParser.parse(companyList.get(i).getCompanydata());
                jArray = (JSONArray) jObject.get("documents");
                jObject = (JSONObject) jArray.get(0);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            List<CompanyDTO> siteCountList = service.matzipCount(companyList.get(i).getLocation());

            companyDTO.setId(companyList.get(i).getId());
            companyDTO.setLocation(companyList.get(i).getLocation());
            companyDTO.setMatzipCount(siteCountList.get(0).getMatzipCount());
            companyDTO.setCompanyName((String) jObject.get("place_name"));
            companyDTO.setCompanyLoc((String) jObject.get("address_name"));

            selectList.add(companyDTO);
        }
        selectCompanyMap.put("company", selectList);


        return selectCompanyMap;
    }

    @GetMapping(value="/matzipDetailId")
    public Map<String, List<CompanyDTO>> matzipDetailId(@RequestParam("loc") String loc,
                                                       CompanyDTO companyDTO) {
        Map<String, List<CompanyDTO>> selectCompanyMap = new HashMap<>();

        System.out.println("loc : "+loc);
        List<String> matzipData = service.matzipData();

        for (int i=0; i<matzipData.size(); i++) {
            System.out.println(matzipData);

        }

        for (int i=0; i<matzipData.size(); i++) {

            try {
                jObject = (JSONObject) jParser.parse(matzipData.get(i));
                jArray = (JSONArray) jObject.get("documents");
                jObject = (JSONObject) jArray.get(0);

                if (((String) jObject.get("address_name")).equals(loc)) {

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        return selectCompanyMap;
    }


}
