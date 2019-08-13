package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.TpSubtitleContentMapper;
import com.gameloft9.demo.dataaccess.model.system.TpSubtitleContent;
import com.gameloft9.demo.service.api.system.TpSubtitleContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Controller
@Service
public class TpSubtitleContentServiceImpl implements TpSubtitleContentService {
    @Autowired
    TpSubtitleContentMapper DAO;
    public List<TpSubtitleContent> findBybigTitleId(int bigTitleId) {
        return DAO.findBybigTitleId(bigTitleId);
    }

    public List<TpSubtitleContent> findBybigTitleId1(int bigTitleId) {
        return DAO.findBybigTitleId1(bigTitleId);
    }

    public int delete(int bigTitleId) {
        return DAO.delete(bigTitleId);
    }
}
