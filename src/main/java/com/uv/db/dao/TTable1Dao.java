package com.uv.db.dao;

import com.uv.db.entity.TTable1;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author uvsun 2018/9/12 下午1:12
 */
@Repository
public interface TTable1Dao {

    List<TTable1> selectAll();

    void add(TTable1 tTable1);

    int deleteAll();
}
