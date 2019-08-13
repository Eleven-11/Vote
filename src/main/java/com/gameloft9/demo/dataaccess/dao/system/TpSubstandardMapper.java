package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.TpSubstandard;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TpSubstandardMapper {
    List<TpSubstandard> findBystandardId(int standardId);
    List<TpSubstandard> findBystandardId1(int standardId);
    int delete(int standardId);
}
