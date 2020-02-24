package com.jawaw.myclub.controller

import com.github.pagehelper.PageInfo
import com.github.pagehelper.page.PageMethod
import com.jawaw.myclub.service.OpService
import com.jawaw.myclub.vo.ResponseVO
import io.swagger.annotations.ApiOperation
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._

@Controller
@RequestMapping(value = Array("/admin/oplist/"))
class OpController @Autowired()(val opService: OpService) {

  @ResponseBody
  @ApiOperation(value = "分页获取俱乐部管理员列表", notes = "")
  @RequestMapping(value = Array("{pageNum}/{pageSize}"), method = Array(RequestMethod.GET))
  def getClubListByPage(@PathVariable pageNum: Int, @PathVariable pageSize: Int, request: HttpServletRequest) = {
    PageMethod.startPage(pageNum, pageSize)
    val entitys = opService.opList()
    val pageInfo = new PageInfo(entitys)
    ResponseVO.success(pageInfo)
  }

}
