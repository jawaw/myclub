package com.jawaw.myclub.controller

import java.io.File
import java.util.Date

import com.github.pagehelper.PageInfo
import com.github.pagehelper.page.PageMethod
import com.jawaw.myclub.model.{Coach, Operator}
import com.jawaw.myclub.service.CoachService
import com.jawaw.myclub.vo.ResponseVO
import io.swagger.annotations.ApiOperation
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._

@Controller
@RequestMapping(value = Array("/admin/coaches/"))
class CoachController @Autowired() (val coachService: CoachService) {

  @ResponseBody
  @ApiOperation(value = "分页获取俱乐部教练列表", notes = "")
  @RequestMapping(value = Array("{coach_is_master}/{pageNum}/{pageSize}"), method = Array(RequestMethod.GET))
  def getClubListByPage(@PathVariable coach_is_master: Int,
                        @PathVariable pageNum: Int,
                        @PathVariable pageSize: Int, request: HttpServletRequest) = {
    PageMethod.startPage(pageNum, pageSize)
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    val entitys = coachService.list(opt.club_id, coach_is_master)
    val pageInfo = new PageInfo(entitys)
    ResponseVO.success(pageInfo)
  }

  @ResponseBody
  @ApiOperation(value = "添加俱教练", notes = "")
  @RequestMapping(value = Array("/add"), method = Array(RequestMethod.POST))
  def addCoach(@RequestBody coach: Coach, httpServletRequest: HttpServletRequest) = {
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
      coach.setClub_id(opt.club_id)
      coach.setCoach_is_master(0)
      coach.setCreate_time(new Date(System.currentTimeMillis()))
      coachService.save(coach)
    ResponseVO.success("操作完成", coach)
  }

  @ResponseBody
  @ApiOperation(value = "修改教练信息", notes = "")
  @RequestMapping(value = Array("/update"), method = Array(RequestMethod.PUT))
  def updateClub(coach_id: Int,
                 coach_name: String,
                 coach_desc: String,
                 coach_gender: Int,
                 coach_is_master: Int,
                 httpServletRequest: HttpServletRequest) = {

    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    val coach = coachService.findCoachById(coach_id)
    if (opt.is_Role_Admin() || opt.is_Role_User(coach)) {  // only set Coach to Master by operator who has `ROLE_ADMIN`
      if (opt.is_Role_Admin()) coach.setCoach_is_master(coach_is_master)
      if (opt.is_Role_User(coach) && (coach_is_master == 0 || coach_is_master == 1))
        coach.setCoach_is_master(coach_is_master)
      coach.setCoach_name(coach_name)
      coach.setCoach_desc(coach_desc)
      coach.setCoach_gender(coach_gender)
      coachService.save(coach)
    }
    ResponseVO.success("操作完成", coach)
  }

  @ResponseBody
  @ApiOperation(value = "删除俱教练", notes = "")
  @RequestMapping(value = Array("/{coach_id}"), method = Array(RequestMethod.DELETE))
  def deleteUClub(@PathVariable coach_id: Int, httpServletRequest: HttpServletRequest) = {
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    val coach = coachService.findCoachById(coach_id)
    if (opt.is_Role_Admin() || opt.is_Role_User(coach)) {
      // befor database `delete`, we should remove all resource about this coach
      val basePath = "C:\\Users\\CCU_X\\Desktop\\webapp\\static\\image\\coach\\";
      val file = new File(basePath + coach.coach_pic)
      if (file.exists()) file.delete()
      coachService.deleteCoachById(coach.coach_id)
    }
    ResponseVO.success("操作完成", coach)
  }

}
