package com.jawaw.myclub.dao.mybatis

import com.jawaw.myclub.model.{Operator, OperatorMeta}
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Repository
trait OperatorMapper {

  @Select(Array("select * from operator where username=#{username}"))
  def loadUserByUsername(username: String): Operator

//  @Select(Array("select * from operator"))
  def getAllOperator(): java.util.List[OperatorMeta]

  @Select(Array("select * from operator where club_id=#{club_id}"))
  def findByClubId(club_id: Int): Operator

}
