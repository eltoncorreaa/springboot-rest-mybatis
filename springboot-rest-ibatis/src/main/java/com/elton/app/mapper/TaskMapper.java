package com.elton.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.elton.app.model.Task;

@Mapper
public interface TaskMapper {

	@Select("select * from task where id = #{id}")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	Task findById(@Param("id") Long id);

	@Select("select * from task")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	List<Task> findAll();

	@Insert("INSERT into TASK(id, name, startDate, version, creationTime) VALUES(#{id}, #{name}, #{startDate}, 0, CURRENT_TIMESTAMP)")
	@SelectKey(statement="SELECT TASK_SEQUENCE.NEXTVAL FROM DUAL", keyProperty="id", before=true, resultType=Long.class)
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	Long insert(Task task);

	@Update("UPDATE TASK SET name=#{name}, startDate=#{startDate}, version=version+1, lastUpdateTime=CURRENT_TIMESTAMP WHERE id =#{id} and version=#{version}")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	Long update(Task task);

	@Delete("DELETE FROM TASK WHERE id =#{id}")
	@Options(flushCache=FlushCachePolicy.DEFAULT)
	void delete(Long id);
}
