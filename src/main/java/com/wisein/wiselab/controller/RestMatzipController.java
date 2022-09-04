package com.wisein.wiselab.controller;

import com.wisein.wiselab.config.JsonInstance;
import com.wisein.wiselab.dao.MatzipDAO;
import com.wisein.wiselab.dto.CompanyDTO;
import com.wisein.wiselab.service.MatzipService;
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
    MatzipDAO dao;

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

        List<CompanyDTO> siteList = dao.companyList();
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

            List<CompanyDTO> siteCountList = dao.matzipCount(siteList.get(i).getLocation());

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

    @GetMapping(value="/foodList")
    public Map<String, List<CompanyDTO>> matzipList(CompanyDTO companyDTO,Model model,
                                                     @RequestParam("location") String location) {

        List<CompanyDTO> company = new ArrayList<>();
        List<CompanyDTO> matzipList = dao.matzipList(location);

        Map<String, List<CompanyDTO>> map = new HashMap<>();

        for (int i=0; i<matzipList.size(); i++) {
            companyDTO = new CompanyDTO();

            List<CompanyDTO> recmMatzipList = dao.recmMatzipCount(matzipList.get(i).getId());

            try {
                jObject = (JSONObject) jParser.parse(matzipList.get(i).getCompanydata());

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


        map.put("matzip", company);

        return map;
    }

    //위도 경도 변환
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
            System.out.println(response);
            foodLocalMap.put("local", response.toString());

            return foodLocalMap;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value="/matzipDetailId")
    public int matzipDetailId(@RequestParam("loc") String loc) {

        int matzipId = dao.matzipId(loc);
        return matzipId;
    }

    @GetMapping(value="/matzipCheck")
    public int matzipCheck(@RequestParam("id") int id) {

        int check = dao.matzipExistCheck(id);
        return check;
    }

    @GetMapping(value="/categorySelect")
    public Map<String, List<CompanyDTO>> categorySelect(CompanyDTO companyDTO,
                                                        @RequestParam("option") String option) {

        System.out.println(option);
        List<CompanyDTO> siteList = dao.categoryDetail(option);
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

            List<CompanyDTO> siteCountList = dao.matzipCount(siteList.get(i).getLocation());

            companyDTO.setId(siteList.get(i).getId());
            companyDTO.setLocation(siteList.get(i).getLocation());
            companyDTO.setMatzipCount(siteCountList.get(0).getMatzipCount());
            companyDTO.setCompanyName((String) jObject.get("place_name"));
            companyDTO.setCompanyLoc((String) jObject.get("address_name"));

            company.add(companyDTO);
        }
        System.out.println(company);
        map.put("company", company);
        return map;

    }


}
