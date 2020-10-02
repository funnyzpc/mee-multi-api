package com.mee.dao;

import com.mee.common.util.JacksonUtil;
import com.mee.common.util.SeqGenUtil;
import com.mee.core.dao.DB1SQLDao;
import com.mee.core.dao.DB2SQLDao;
import com.mee.core.dao.DB3SQLDao;
import com.mee.core.model.MapOrclBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("dev")
public class TmpTest {

    @Resource
    private DB1SQLDao db1SQLDao;
    @Resource
    private DB2SQLDao db2SQLDao;
    @Resource
    private DB3SQLDao db3SQLDao;

    @Test
    public void insert(){
        Map<String,Object> insetParam = new HashMap<String,Object>(2,1){{
            put("id", SeqGenUtil.genSeq());
        }};
        System.out.println("写入数据: "+ JacksonUtil.toJsonString(insetParam));
        insetParam.put("name","I'm DB1");
        int  insert1Count = db1SQLDao.create("com.mee.xml1.tmp.insert",insetParam);
        insetParam.put("name","I'm DB2");
        int  insert2Count = db2SQLDao.create("com.mee.xml2.tmp.insert",insetParam);
        insetParam.put("name","I'm DB3");
        int  insert3Count = db3SQLDao.create("com.mee.xml3.tmp.insert",insetParam);
        System.out.println("======>insert1Count:"+insert1Count);
        System.out.println("======>insert2Count:"+insert2Count);
        System.out.println("======>insert3Count:"+insert3Count);
    }


    @Test
    public void query(){
        Map<String,Object> insetParam = new HashMap<String,Object>(1,1){{
            put("id", "2010021540029000");
        }};
        List<Map> db1Result = db1SQLDao.query("com.mee.xml1.tmp.findList",insetParam);
        List<Map>  db2Result = db2SQLDao.query("com.mee.xml2.tmp.findList",insetParam);
        List<MapOrclBean>  db3Result = db3SQLDao.query("com.mee.xml3.tmp.findList",insetParam);
        System.out.println("======>db1Result:"+db1Result);
        System.out.println("======>db2Result:"+db2Result);
        System.out.println("======>db3Result:"+db3Result);
    }

    @Test
    @Transactional(rollbackFor = Exception.class,transactionManager = "allTransactionManager")
    public void transaction(){
        doInsert();
    }

    public void doInsert(){
        Map<String,Object> insetParam = new HashMap<String,Object>(2,1){{
            put("id", SeqGenUtil.genSeq());
            put("name","hello youth ~");
        }};
        System.out.println("待写入数据: "+ JacksonUtil.toJsonString(insetParam));
        int  insert1Count = db1SQLDao.create("com.mee.xml1.tmp.insert",insetParam);
        int  insert2Count = db2SQLDao.create("com.mee.xml2.tmp.insert",insetParam);
        int  insert3Count = db3SQLDao.create("com.mee.xml3.tmp.insert",insetParam);
        Integer[] intArr = new Integer[]{1,2,33};
        System.out.println(intArr[10]);
    }

}
