package com.jawaw.myclub.dao.mybatis

import com.jawaw.myclub.model.Club
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Repository
trait ClubMapper {

//  @Select(Array("select * from club "))
  def list(@Param("id") id: Int): java.util.List[Club]

//  @Select(Array("select * from club where club_name like CONCAT('%',#{name},'%')"))
  def searchClubByName(@Param("name") name: String, @Param("club_id") club_id: Int): java.util.List[Club]

}
