package com.mee.common.service;

/**
 * @author funnyzpc
 * @description 序列生成器
 */
public interface SeqGenService {


    /** 生成主键：14(日期时间)+4(端口)+6(有序序列) **/
    String genPrimaryKey();

    /** 生成短主键:10位(时间戳)+4位(端口号)+4位(有序序列) **/
    String genShortPrimaryKey();

}
