package org.nsop.jusodemo.service.impl;

import org.nsop.jusodemo.service.JusoDataService;
import org.nsop.jusodemo.service.JusoDataVO;
import jakarta.annotation.Resource;
import org.nsop.jusodemo.service.MergeJusoDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("jusoDataService")
public class JusoDataServiceImpl implements JusoDataService {
    //Mapper 선언
    @Resource(name="jusoDataMapper")
    private JusoDataMapper jusoDataMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<JusoDataVO> selectJusoList() {
        return jusoDataMapper.selectJusoList();
    }

    @Override
    public void insertMergeVWJuso(MergeJusoDataVO vo) {
        jusoDataMapper.insertMergeVWJuso(vo);
    }

    @Override
    @Transactional
    public void insertMergeVWJusoList(List<MergeJusoDataVO> mergeJusoDataList) {
        jusoDataMapper.insertMergeVWJusoList(mergeJusoDataList);
    }

}
