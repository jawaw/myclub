package com.jawaw.myclub.dao.mybatis

import com.jawaw.myclub.model.User
import org.apache.ibatis.annotations.Select

trait UserMapper {

  @Select(Array("select * from user where user_name like CONCAT('%',#{name},'%')"))
  def searchUserByName(name: String): java.util.List[User]

}
