package com.jawaw.myclub.service

import com.jawaw.myclub.model.Menu


trait MenuService {

  def getMenusByRole(id: Int): java.util.List[Menu]

}
