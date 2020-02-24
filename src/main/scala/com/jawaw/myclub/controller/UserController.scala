package com.jawaw.myclub.controller

import com.github.pagehelper.PageInfo
import com.github.pagehelper.page.PageMethod
import com.jawaw.myclub.service.UserService
import com.jawaw.myclub.vo.ResponseVO
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._

@Controller
@RequestMapping(value = Array("/admin/users/"))
class UserController @Autowired() (val userService: UserService) {

  @ResponseBody
  @ApiOperation(value = "分页获取用户列表", notes = "")
  @RequestMapping(value = Array("{pageNum}/{pageSize}"), method = Array(RequestMethod.GET))
  def getClubListByPage(@PathVariable pageNum: Int, @PathVariable pageSize: Int) = {
    PageMethod.startPage(pageNum, pageSize)
    val entitys = userService.list()
    val pageInfo = new PageInfo(entitys)
    ResponseVO.success(pageInfo)
  }

  @ResponseBody
  @ApiOperation(value = "查看用户详情", notes = "")
  @RequestMapping(value = Array("/{id}"), method = Array(RequestMethod.GET))
  def getClub(@PathVariable id: Int) = {
    val entity = userService.findUserById(id)
    ResponseVO.success(entity)
  }

  @ResponseBody
  @ApiOperation(value = "锁定用户", notes = "test")
  @RequestMapping(value = Array("/lock/{id}"), method = Array(RequestMethod.PUT))
  def lockUser(@PathVariable id: Int) = {
    userService.deleteUserById(id)
    ResponseVO.success(null)
  }

  @ResponseBody
  @ApiOperation(value = "封禁用户", notes = "test")
  @RequestMapping(value = Array("/ban/{id}"), method = Array(RequestMethod.PUT))
  def enableUser(@PathVariable id: Int) = {
    userService.deleteUserById(id)
    ResponseVO.success(null)
  }

  @ResponseBody
  @ApiOperation(value = "搜索用户", notes = "test")
  @RequestMapping(value = Array("/search"), method = Array(RequestMethod.GET))
  def searchClub(@RequestParam(value = "user_name", required = true, defaultValue = "$") userName: String) = {
    val entitys = userService.searchUser(userName)
    val pageInfo = new PageInfo(entitys)
    ResponseVO.success(pageInfo)
  }

}
