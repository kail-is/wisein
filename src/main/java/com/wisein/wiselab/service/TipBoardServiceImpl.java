package com.wisein.wiselab.service;

import com.wisein.wiselab.common.FileUtils;
import com.wisein.wiselab.dao.MemberDAO;
import com.wisein.wiselab.dao.TipBoardDAO;
import com.wisein.wiselab.dto.FileDTO;
import com.wisein.wiselab.dto.TipBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
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
    public List<TipBoardDTO> selectTipList() throws Exception {
        return dao.selectTipList();
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

    /* TipBoard 이미지 url*/
    @Override
    public String imgUrlReg(MultipartHttpServletRequest multipartHttpServletRequest, HttpSession session) throws Exception{
        if(ObjectUtils.isEmpty(multipartHttpServletRequest) == false) {

            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
            String name;
            while(iterator.hasNext()) {
                name = iterator.next();
                List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
                for(MultipartFile multipartFile : list) {

                    String contType =  multipartFile.getContentType();
                    String[] contArr = contType.split("/");
                    String extension = contArr[1];
                }

            }
        }

        String brdRef =  "tip||"+dao.selectTipNum();
        List<FileDTO> list = fileUtils.parseFileInfo(brdRef, "image", multipartHttpServletRequest);
        if(CollectionUtils.isEmpty(list) == false) {
            memDao.insertMemFileList(list);
        }
        String imgUrl = list.get(0).getFilePath();

        return imgUrl;
    }


}
