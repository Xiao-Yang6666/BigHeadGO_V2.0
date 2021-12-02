package com.bigheadgo.dao.imp;

import com.bigheadgo.dao.Dao;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * dao 的实现类
 * author: xiaoYang
 * time: 2021/12/1 21:16
 */
@Repository
@Slf4j
public class SupportDao implements Dao {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<?> findForList(String mapper, String mapperId, Object obj) {
        return sqlSessionTemplate.selectList(mapper + "." + mapperId, obj);
    }

    @Override
    public Object findForObject(String mapper, String mapperId, Object obj) {
        return sqlSessionTemplate.selectOne(mapper + "." + mapperId, obj);
    }

    @Override
    public boolean insert(String mapper, String mapperId, Object obj) {
        return sqlSessionTemplate.insert(mapper + "." + mapperId, obj) != 0;
    }

    @Override
    public boolean update(String mapper, String mapperId, Object obj) {
        return sqlSessionTemplate.update(mapper + "." + mapperId, obj) != 0;
    }

    @Override
    public boolean delete(String mapper, String mapperId, Object obj) {
        return sqlSessionTemplate.delete(mapper + "." + mapperId, obj) != 0;
    }

    @Override
    public boolean batchInsert(String mapper, String mapperId, List<Object> objList) {
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        //批量执行器
        try {
            if (objList != null) {
                for (int i = 0, size = objList.size(); i < size; i++) {
                    sqlSession.update(mapper + "." + mapperId, objList.get(i));
                }
                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
            }
            return true;
        } catch (Exception e) {
            sqlSession.rollback();
            log.error("批量保存失败数据回滚....");
            return false;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public boolean batchUpdate(String mapper, String mapperId, List<Object> objList) {
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        //批量执行器
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try {
            if (objList != null) {
                for (int i = 0, size = objList.size(); i < size; i++) {
                    sqlSession.insert(mapper + "." + mapperId, objList.get(i));
                }
                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
            }
            return true;
        } catch (Exception e) {
            sqlSession.rollback();
            log.error("批量修改失败数据回滚....");
            return false;
        } finally {
            sqlSession.close();
        }
    }
}
