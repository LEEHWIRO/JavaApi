package org.nsop.jusodemo.service;

import java.util.List;

public interface JusoDataService {

    List<JusoDataVO> selectJusoList();

    void insertMergeVWJuso(MergeJusoDataVO vo);

    void insertMergeVWJusoList(List<MergeJusoDataVO> voList);

}
