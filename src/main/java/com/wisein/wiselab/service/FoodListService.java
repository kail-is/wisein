package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.CompanyDTO;
import com.wisein.wiselab.dto.FoodListDTO;

import java.util.List;

public interface FoodListService {

   public List<CompanyDTO> companyList();
   public List<CompanyDTO> matzipCount(String location);
   public List<CompanyDTO> matzipList(String location);
   public List<CompanyDTO> recmMatzipCount(int id);
   public List<CompanyDTO> company();
   public List<CompanyDTO> selectCompany(String location);
   public List<String> matzipData();

}

