
package com.mee.core.dao.impl;


import com.mee.common.entity.BaseEntity;
import com.mee.common.service.impl.SeqGenServiceImpl;
import com.mee.common.util.DateUtil;
import com.mee.common.util.JacksonUtil;
import com.mee.core.dao.DB1SQLDao;
import com.mee.core.model.Page;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author funnyzpc
 * @description sql DAO配置 PG
 */
@Repository
public class DB1SQLDaoImpl implements DB1SQLDao {
    private static final Logger log = LoggerFactory.getLogger(DB1SQLDaoImpl.class);

    @Autowired
    private SeqGenServiceImpl seqGenService;

    @Autowired
    @Qualifier("db1SqlSessionTemplate")
    private SqlSessionTemplate sqlSession;

    public SqlSessionTemplate getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }
    @Override
    public List query(String id) {
        return sqlSession.selectList(id);
    }
    @Override
    public List query(String id, int count) {
        return sqlSession.selectList(id, null, new RowBounds(0, count));
    }

    @Override
    public List query(String id, Map params) {
        return sqlSession.selectList(id, params);
    }
    @Override
    public List query(String id, Map params, int count) {
        return sqlSession.selectList(id, params, new RowBounds(0, count));
    }

    public <T> T queryOne(String id, Map params){
        return sqlSession.selectOne(id,params);
    }

    @Override
    public int create(String id, Map params) {
        return sqlSession.insert(id, params);
    }

    @Override
    public <P extends BaseEntity> String create(String id, P params) {
        if(null != params.getId() && !"".equals(params.getId())){
            log.error("记录已存在：{}", JacksonUtil.toJsonString(params));
            return null;
        }
        // 设置ID、创建人、创建日期
        params.setId(seqGenService.genPrimaryKey());
        params.setCreate_date(DateUtil.now());
        if(null == params.getCreate_by()){
            //params.setCreate_by(ShiroUtils.getUserId());
        }
        if(sqlSession.insert(id, params)>0){
            return  params.getId();
        }
        return null;
    }
    @Override
    public int update(String id, Map params) {
        return sqlSession.update(id, params);
    }

    public <P extends BaseEntity> int update(String id, P params) {
        // 空ID不更新
        if(null == params.getId() || "".equals(params.getId().trim())){
            log.error("更新记录ID为空：{}", JacksonUtil.toJsonString(params));
            return 0;
        }
        return sqlSession.update(id, params);
    }

    @Override
    public int delete(String id, Map params) {
        return sqlSession.delete(id, params);
    }

    @Override
    public List query(String id, List list) {
        return sqlSession.selectList(id, list);
    }

    @Override
    public Page list(String id, int pageIdx, int pageSize) {
        return list(id, null, pageIdx, pageSize);
    }

    @Override
    public Page list(String id, Map params, int pageIdx, int pageSize) {
        Page<Object> p = new Page<>(params, pageIdx, pageSize);
        p.setData(sqlSession.selectList(id, p));
        return p;
    }
}
