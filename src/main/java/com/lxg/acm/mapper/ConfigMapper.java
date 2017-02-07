package com.lxg.acm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.lxg.acm.entity.Config;

@Repository
public interface ConfigMapper{

	// 添加config
	@Insert("insert into `config` (`key`, `value`, `description`, `createTime`, `modifyTime`) values (#{key},#{value}, '', now(), now())")
	public void save(Config config);

	// 按key查询config
	@Select("select * from `config` where `key`=#{key}")
	public Config query(String key);

	// 查询所有config
	@Select("select * from `config`")
	public List<Config> queryForList(Object param, Long offset, Long pagesize);

	// 按key删除config
	@Delete("delete from `config` where `key`=#{key}")
	public void delete(Config config);

	// 更新config
	@Update("update `config` set `value`=#{value}, `description`=#{description}, `modifyTime`=now()  where `key`=#{key}")
	public void update(Config config);

}
