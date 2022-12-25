package com.wisein.wiselab.service;

import com.wisein.wiselab.common.FileUtils;
import com.wisein.wiselab.dao.MemberDAO;
import com.wisein.wiselab.dto.FileDTO;
import com.wisein.wiselab.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private MemberDAO dao;

    @Autowired
    BCryptPasswordEncoder passEncoder;

    @Override
    public void register(MemberDTO dto) throws Exception {
        dao.register(dto);
    }

    @Override
    public int idDupChk(String userId) throws Exception {
        return dao.idDupChk(userId);
    }

    @Override
    public MemberDTO login(MemberDTO dto) throws Exception {
        return dao.login(dto);
    }

    @Override
    public String findTempKey(String id) throws Exception {
        return dao.findTempKey(id);
    }

    @Override
    public void logout(HttpSession session) throws Exception {
        session.invalidate();
    }

    @Override
    public void authStateUpdate(String id) throws Exception {
        dao.authStateUpdate(id);
    }

    @Override
    public int authIdExist(String id) throws Exception {
        return dao.authIdExist(id);
    }

    @Override
    public void modify(MemberDTO dto) throws Exception {

        JSONObject fileInfos = null;
        if (ObjectUtils.isEmpty(dto.getFileInfos()) == false) {
            fileInfos = (JSONObject) JSONValue.parse(dto.getFileInfos());
        }

        if (dto.getPw() != null) {
            dao.modifyPass(dto);
        } else {
            dao.modify(dto);
        }

        String brdRef = "mem||" + dto.getId();
        List<FileDTO> list = fileUtils.parseFileInfo(brdRef, dto.getId(), "image", fileInfos);
        if (CollectionUtils.isEmpty(list) == false) {
            dao.insertMemFileList(list);
        }
    }

    @Override
    public void addChgePw(MemberDTO dto) throws Exception {

        int count = dao.chgePassDupChk(dto.getId());

        if(count > 0){
            dao.addChgePwPlus(dto);
        }else {
            dao.addChgePw(dto);
        }

    }

    @Override
    public void modMemByToken(String token) throws Exception {

        MemberDTO dto = dao.findModMemData(token);
        // 토큰으로 비밀번호 찾기 테이블 조회

        System.out.println("조회:" + dto);

        // 암호화 - 암호화 값 DB 저장 후 재조회 시 다른 문제 해결되면 제거 가능
        String modPw = dto.getPw();
        String passEncd = passEncoder.encode(modPw);
        dto.setPw(passEncd);

        dao.modifyPwOnly(dto);
        // 해당 비밀번호 회원 값에 업데이트
    }

    @Override
    public void delUserImg(String fileNm) throws Exception {
        dao.delUserImg(fileNm);
    }

    @Override
    public List<FileDTO> memImgList(String id) throws Exception {
        String brdRef =  "mem||" + id;
        List<FileDTO> list = dao.selectMemFileList(brdRef);
        return list;
    }

    @Override
    public void withdraw(MemberDTO dto, HttpSession session) throws Exception {
        dao.withdraw(dto);
        session.invalidate();
    }


}