package com.myhexin.dao.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myhexin.entity.PctMetal;

/**
 *
 */
public interface IProductDao {

    public List<PctMetal> queryPctMetal(@Param("pctcode") String pctcode);

    public void insertPctMetal(PctMetal pctMetal);
}
