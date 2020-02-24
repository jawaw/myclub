package com.jawaw.myclub.controller

import java.io.{File, IOException}
import java.util.{Date, UUID}

import com.github.pagehelper.PageInfo
import com.github.pagehelper.page.PageMethod
import com.jawaw.myclub.model.{Club, Operator}
import com.jawaw.myclub.service.{ClubService, OpService}
import com.jawaw.myclub.vo.ResponseVO
import io.swagger.annotations.ApiOperation
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._
import org.springframework.web.multipart.MultipartFile

@Controller
@RequestMapping(value = Array("/admin/clubs/"))
class ClubController @Autowired()(val clubService: ClubService,
                                  val opService: OpService) {

  @ResponseBody
  @ApiOperation(value = "分页获取俱乐部列表", notes = "")
  @RequestMapping(value = Array("{pageNum}/{pageSize}"), method = Array(RequestMethod.GET))
  def getClubListByPage(@PathVariable pageNum: Int, @PathVariable pageSize: Int, request: HttpServletRequest) = {
    PageMethod.startPage(pageNum, pageSize)
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    val entitys = clubService.list(opt.club_id)
    val pageInfo = new PageInfo(entitys)
    ResponseVO.success(pageInfo)
  }

  @ResponseBody
  @ApiOperation(value = "查看俱乐部", notes = "")
  @RequestMapping(value = Array("/{id}"), method = Array(RequestMethod.GET))
  def getClub(@PathVariable id: Int, httpServletRequest: HttpServletRequest) = {
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    val entity = clubService.findClubById(id)
    ResponseVO.success(entity)
  }

  @ResponseBody
  @ApiOperation(value = "添加俱乐部", notes = "")
  @RequestMapping(value = Array("/add"), method = Array(RequestMethod.POST))
  def addClub(@RequestBody club: Club, httpServletRequest: HttpServletRequest) = {
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    if (opt.is_Role_Admin()) {
      club.setCreate_time(new Date(System.currentTimeMillis()))
      clubService.createClub(club)
    }
    ResponseVO.failure("操作完成", club)
  }

  @ResponseBody
  @ApiOperation(value = "修改俱乐部", notes = "")
  @RequestMapping(value = Array("/update"), method = Array(RequestMethod.PUT))
  def updateClub(club_id: Int,
                 club_name: String,
                 club_desc: String,
                 club_contact: String,
                 club_address: String,
                 club_manager: String,
                 club_status: Int,
                 httpServletRequest: HttpServletRequest) = {
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    val club = clubService.findClubById(club_id)
    if (opt.is_Role_Admin() || (opt.club_id == club_id)) {
      club.setClub_name(club_name)
      club.setClub_desc(club_desc)
      club.setClub_contact(club_contact)
      club.setClub_address(club_address)
      club.setClub_manager(club_manager)
      club.setClub_status(club_status)
      club.setUpdate_time(new Date(System.currentTimeMillis()))
      clubService.updateClubById(club)
    }
    ResponseVO.failure("修改失败", club)
  }

  @ResponseBody
  @ApiOperation(value = "上传俱乐部图片", notes = "")
  @RequestMapping(value = Array("/upload"), method = Array(RequestMethod.POST))
  def uploadImage(club_id: Int, file: MultipartFile,
                  request: HttpServletRequest) = {
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    if (opt.is_Role_Admin() || (opt.club_id == club_id)) {
      val fileName = file.getOriginalFilename
      var picName = UUID.randomUUID().toString + "." + fileName.split("\\.")(1)
      val basePath = "C:\\Users\\CCU_X\\Desktop\\webapp\\static\\image\\club\\";
      var pic = new File(basePath + picName)
      try {
        file.transferTo(pic)
      } catch {
        case ex: IOException => println("IO Exception")
      }
      val club = clubService.findClubById(club_id)
      if (!Array("1.jpg", "2.jpg", "3.jpg").contains(club.club_pic)) {
        val oldPic = new File(basePath + club.club_pic)
        if (oldPic.exists()) oldPic.delete();
      }
      club.setClub_pic(picName)
      clubService.updateClubById(club)
    }
    ResponseVO.failure("操作完成", clubService.findClubById(club_id))
  }

  @ResponseBody
  @ApiOperation(value = "删除俱乐部", notes = "test")
  @RequestMapping(value = Array("/{id}"), method = Array(RequestMethod.DELETE))
  def deleteUClub(@PathVariable id: Int, httpServletRequest: HttpServletRequest) = {
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    val club = clubService.findClubById(id)
    if (opt.is_Role_Admin()) {       // befor database `delete`, we should remove all resource about this club
      val basePath = "C:\\Users\\CCU_X\\Desktop\\webapp\\static\\image\\coach\\";
      val file = new File(basePath + club.club_pic)
      if (file.exists()) file.delete()
      clubService.deleteClubById(id)
//      club.setClub_status(0)
//      clubService.updateClubById(club)
    }
    ResponseVO.success("操作完成", club)
  }

  @ResponseBody
  @ApiOperation(value = "关停/恢复 俱乐部", notes = "")
  @RequestMapping(value = Array("/forbidden"), method = Array(RequestMethod.PUT))
  def forbiddenClub(club_id: Int,
                 club_status: Int,
                 httpServletRequest: HttpServletRequest): ResponseVO[Any] = {
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    val club = clubService.findClubById(club_id)
    if (opt.is_Role_Admin()) {
      val clubOperator = opService.findByClubId(club_id)
      if (club_status == 0) clubOperator.setLocked(true)
      if (club_status == 1) clubOperator.setLocked(false)
      opService.save(clubOperator)
      club.setClub_status(club_status)
      club.setUpdate_time(new Date(System.currentTimeMillis()))
      clubService.updateClubById(club)
      return ResponseVO.success("操作成功", club)
    }
    return ResponseVO.failure("操作失败", club)
  }

  @ResponseBody
  @ApiOperation(value = "搜索俱乐部", notes = "test")
  @RequestMapping(value = Array("/search"), method = Array(RequestMethod.GET))
  def searchClub(@RequestParam(value = "club_name", required = true, defaultValue = "$") clubName: String,
                 @RequestParam(value = "pageNum") pageNum: Int,
                @RequestParam(value = "pageSize") pageSize: Int) = {

    println("-----------------》" + clubName)
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    PageMethod.startPage(pageNum, pageSize)
    val entitys = clubService.searchClub(clubName, opt.club_id)
    val pageInfo = new PageInfo(entitys)
    ResponseVO.success(pageInfo)
  }

}
