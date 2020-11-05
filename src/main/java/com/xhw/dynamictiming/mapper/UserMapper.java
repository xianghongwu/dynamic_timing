package com.xhw.dynamictiming.mapper;

import com.xhw.dynamictiming.model.SysJob;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface  UserMapper {

    @Select("select * from db_user")
    List<SysJob> getUserlist();
}
