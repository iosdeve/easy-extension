package com.xin.easyextension.mapper;

import com.xin.easyextension.domain.App;
import java.util.List;

public interface AppMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(App record);

    App selectByPrimaryKey(Integer id);

    List<App> selectAll();

    int updateByPrimaryKey(App record);
}