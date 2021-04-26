package com.papaxiong.mapper;

import com.papaxiong.model.po.IdGeneratorDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author zhaoqi
 * @since 2021-04-13
 */
@Mapper
@Repository
public interface IdGeneratorMapper {

    IdGeneratorDO getOne(@Param("id") Long id);


    //!hasResultHandler
    @Select("select * from id_generator where id=#{id}")
    IdGeneratorDO getOneBySimple(@Param("id") Long id);






    int updateByVersion(@Param("generator") IdGeneratorDO update,@Param("oldVersion") Integer oldVersion);

}
