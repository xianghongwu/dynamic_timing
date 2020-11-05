package com.xhw.dynamictiming.mapper;

import com.xhw.dynamictiming.model.SysJob;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface  UserMapper {

    @Insert("insert into sys_job values(#{jobId},#{beanName},#{methodName},#{methodParams},#{cronExpression},#{jobStatus},#{remark},#{createTime, jdbcType=TIMESTAMP},#{updateTime, jdbcType=TIMESTAMP})")
    int addSysJob(SysJob sysJob);

    @Delete("delete from sys_job where job_id=#{jobId}")
    int delSysJob(String jobId);

    @Select("select * from sys_job where job_id=#{jobId}")
    SysJob getSysJobById(String jobId);

    @Select("select * from sys_job ")
    List<SysJob> getSysJobAll();

    @Update("update sys_job set job_status=#{status} where job_id=#{jobId}")
    int updateSysJob(String jobId,Integer status);
}
