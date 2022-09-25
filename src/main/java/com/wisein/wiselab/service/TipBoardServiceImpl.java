package com.wisein.wiselab.service;

import com.wisein.wiselab.common.FileUtils;
import com.wisein.wiselab.dao.MemberDAO;
import com.wisein.wiselab.dao.TipBoardDAO;
import com.wisein.wiselab.dto.FileDTO;
import com.wisein.wiselab.dto.TipBoardDTO;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TipBoardServiceImpl implements TipBoardService {

    @Autowired
    private TipBoardDAO dao;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private MemberDAO memDao;

    /* TipBoard 다건조회 */
    @Override
    public List<TipBoardDTO> selectTipList(TipBoardDTO dto) throws Exception {
        List<TipBoardDTO> tipList = new ArrayList<>();
        int boardTotalCount = dao.selectBoardTotalCount(dto);

        if(boardTotalCount > 0) {
            tipList = (List<TipBoardDTO>) dao.selectTipList(dto);
        }

        return tipList;
    }

    /* TipBoard 단건조회 */
    @Override
    public TipBoardDTO selectTipOne(TipBoardDTO dto) throws Exception {
        return dao.selectTipOne(dto);
    }

    /* TipBoard 게시글 등록 */
    @Override
    public void insertTipBoard(TipBoardDTO dto) throws Exception {
        dao.insertTipBoard(dto);
    }

    /* TipBoard 게시글 삭제 */
    @Override
    public void deleteTipBoard(int num) throws Exception {
        dao.deleteTipBoard(num);
    }

    /* TipBoard 게시글 수정 */
    @Override
    public void updateTipBoard(TipBoardDTO dto) throws Exception {
        dao.updateTipBoard(dto);
    }

    /* TipBoard 게시글 번호 조회 */
    @Override
    public int selectTipPostNum(TipBoardDTO dto) throws Exception {
        return dao.selectTipPostNum(dto);
    }

    /* 게시글 개수 조회 */
    @Override
    public int selectBoardTotalCount(TipBoardDTO dto) throws Exception {
        return dao.selectBoardTotalCount(dto);
    }


//    /* TipBoard 이미지 url*/
//    @Override
//    public String imgUrlReg(MultipartHttpServletRequest multipartHttpServletRequest, HttpSession session) throws Exception{
//        if(ObjectUtils.isEmpty(multipartHttpServletRequest) == false) {
//
//            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
//            String name;
//            while(iterator.hasNext()) {
//                name = iterator.next();
//                List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
//                for(MultipartFile multipartFile : list) {
//
//                    String contType =  multipartFile.getContentType();
//                    String[] contArr = contType.split("/");
//                    String extension = contArr[1];
//                }
//
//            }
//        }
//
//        int tipNum = 0;
//        String brdRef = "";
//
//        TipBoardDTO dto = (TipBoardDTO) session.getAttribute("TipBoardDTO");
//        if(dto != null){
//            tipNum = dto.getNum();
//            brdRef = "tip||"+tipNum;
//        }else{
//            tipNum = dao.selectNextTipNum();
//            brdRef = "tip||"+tipNum;
//        }
//
////        List<FileDTO> list = fileUtils.parseFileInfo(brdRef, "image", multipartHttpServletRequest);
////        if(CollectionUtils.isEmpty(list) == false) {
////            memDao.insertMemFileList(list);
////        }
////        String imgUrl = list.get(0).getFilePath();
////        return imgUrl;
//        return "";
//    }


}
