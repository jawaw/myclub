package com.jawaw.myclub.service.impl

import com.jawaw.myclub.dao.mybatis.MenuMapper
import com.jawaw.myclub.model.Menu
import com.jawaw.myclub.service.MenuService
import org.springframework.beans.factory.annotation.Autowired


class MenuServiceImpl @Autowired()(val menuMapper: MenuMapper) extends MenuService{

  override def getMenusByRole(id: Int): java.util.List[Menu] = {
    menuMapper.getMenusByRole(id)
  }


}
