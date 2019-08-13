package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.*;


import java.util.List;

public interface TpBigTitleService {
    int delete(int bigTitleId);
    TpBigTitle findbybigid(int bigTitleId);
}
