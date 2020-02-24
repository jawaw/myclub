package com.jawaw.myclub.dao.mybatis

import com.jawaw.myclub.model.Menu

trait MenuMapper {

  def getMenusByRole(role_id: Int): java.util.List[Menu]

}
