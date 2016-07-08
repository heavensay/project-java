package mybatis.dao;

import java.util.List;

import mybatis.PermissionDTO;

import org.apache.ibatis.annotations.Param;

public interface IPermissionDao {
	
	public PermissionDTO queryPermissionById(@Param("id")Integer id);
}
