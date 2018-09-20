package com.uv.db.service;

import com.uv.db.dao.TTable1Dao;
import com.uv.db.entity.TTable1;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author uvsun 2018/9/12 下午2:03
 */
@Service
public class TTable1Service {
    @Resource
    private TTable1Dao tTable1Dao;

    public void add(TTable1 tTable1) {
        tTable1Dao.add(tTable1);
    }

    public void batchAdd(int startId, int n) {
        for (int i = 0; i < n; i++) {
            TTable1 t = new TTable1(startId + i, "n" + (startId + i));
            tTable1Dao.add(t);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {

            }
        }
    }

    public int deleteAll(){
        return this.tTable1Dao.deleteAll();
    }
}
