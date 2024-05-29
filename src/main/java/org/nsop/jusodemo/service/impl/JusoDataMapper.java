package org.nsop.jusodemo.service.impl;

import org.nsop.jusodemo.service.JusoDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.nsop.jusodemo.service.MergeJusoDataVO;
import org.springframework.dao.DataAccessException;

import java.util.List;


@Mapper
public interface JusoDataMapper {

    List<JusoDataVO> selectJusoList() throws DataAccessException;

    void insertMergeVWJuso(MergeJusoDataVO vo) throws DataAccessException;

    void insertMergeVWJusoList(List<MergeJusoDataVO> voList) throws DataAccessException;
}
