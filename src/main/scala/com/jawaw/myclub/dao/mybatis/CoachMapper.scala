package com.jawaw.myclub.dao.mybatis

import com.jawaw.myclub.model.{Coach, CoachMeta}
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

@Repository
trait CoachMapper {

  def list(@Param("id") id: Int, @Param("coach_is_master") coach_is_master: Int): java.util.List[CoachMeta]

  def searchClubByName(name: String): java.util.List[Coach]


}
