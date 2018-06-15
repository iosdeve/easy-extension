package com.xin.easyextension.mapper;

import com.xin.easyextension.domain.LibFile;
import java.util.List;

public interface LibFileMapper {
    int deleteByPrimaryKey(Integer id);
    void deleteByAppId(Integer id);
    int insert(LibFile record);

    LibFile selectByPrimaryKey(Integer id);
    
    LibFile selectByAppId(Integer id);

    List<LibFile> selectAll();

    int updateByPrimaryKey(LibFile record);
}