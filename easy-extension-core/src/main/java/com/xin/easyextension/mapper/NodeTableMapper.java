package com.xin.easyextension.mapper;

import com.xin.easyextension.domain.NodeTable;
import java.util.List;

public interface NodeTableMapper {
    int deleteByPrimaryKey(Integer id);
    void deleteByReferId(Integer id);
    int insert(NodeTable record);

    NodeTable selectByPrimaryKey(Integer id);

    List<NodeTable> selectAll();

    int updateByPrimaryKey(NodeTable record);
}