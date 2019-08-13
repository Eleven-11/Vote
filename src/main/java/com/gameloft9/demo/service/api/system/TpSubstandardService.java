package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.TpSubstandard;

import java.util.List;

public interface TpSubstandardService {
    List<TpSubstandard> findBystandardId(int standardId);
    List<TpSubstandard> findBystandardId1(int standardId);
    int delete(int standardId);
}
