package com.bigheadgo.dao;

import java.util.List;

/**
 * 数据库交互 规范
 * author: xiaoYang
 * time: 2021/12/1 20:46
 */
public interface Dao {
    /**
     * 查询全部 list集合返回
     *
     * @param mapper   mapper名
     * @param mapperId mapper标签id
     * @param obj      参数
     * @return List<?> list?集合
     */
    List<?> findForList(String mapper, String mapperId, Object obj);

    /**
     * 查询单个 object返回
     *
     * @param mapper   mapper名
     * @param mapperId mapper标签id
     * @param obj      参数
     * @return object
     */
    Object findForObject(String mapper, String mapperId, Object obj);

    /**
     * 保存
     *
     * @param mapper   mapper名
     * @param mapperId mapper标签id
     * @param obj      参数
     * @return boolean 是否保存成功
     */
    boolean insert(String mapper, String mapperId, Object obj);

    /**
     * 修改
     *
     * @param mapper   mapper名
     * @param mapperId mapper标签id
     * @param obj      参数
     * @return boolean 是否修改成功
     */
    boolean update(String mapper, String mapperId, Object obj);

    /**
     * 删除 放心调用 本质还是调用的update
     *
     * @param mapper   mapper名
     * @param mapperId mapper标签id
     * @param obj      参数
     * @return boolean 是否删除成功
     */
    boolean delete(String mapper, String mapperId, Object obj);

    /**
     * 批量保存
     *
     * @param mapper   mapper名
     * @param mapperId mapper标签id
     * @param objList  参数列表
     * @return boolean 是否执行成功
     */
    boolean batchInsert(String mapper, String mapperId, List<Object> objList);

    /**
     * 批量修改
     *
     * @param mapper   mapper名
     * @param mapperId mapper标签id
     * @param objList  参数列表
     * @return boolean 是否执行成功
     */
    boolean batchUpdate(String mapper, String mapperId, List<Object> objList);

}
