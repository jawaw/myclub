package com.jawaw.myclub.controller

import com.jawaw.myclub.model.Operator
import com.jawaw.myclub.service.OpService
import com.jawaw.myclub.vo.ResponseVO
import io.swagger.annotations.ApiOperation
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._

@Controller
class OpSecurityController @Autowired()(val opService: OpService) {

  @ResponseBody
  @ApiOperation(value = "管理员注册", notes = "")
  @RequestMapping(value = Array("/register"), method = Array(RequestMethod.POST))
  def reg(@RequestBody op: Operator): ResponseVO[Any] = {
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    if (opt.is_Role_Admin()) {
      if (op.getUsername.endsWith("admin"))
        return ResponseVO.failure("注册失败", None);
      opService.register(op)
    }
    ResponseVO.success(op)
  }

  @ResponseBody
  @ApiOperation(value = "管理员重置密码", notes = "")
  @RequestMapping(value = Array("/reset_pass_word"), method = Array(RequestMethod.POST))
  def reset(@RequestBody op: Operator): ResponseVO[Any] = {
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    return ResponseVO.success(op)
  }

  @ResponseBody
  @ApiOperation(value = "封禁/解封管理员", notes = "")
  @RequestMapping(value = Array("/banop"), method = Array(RequestMethod.PUT))
  def forbiddenOp(username: String,
                  enabled: Boolean,
                  httpServletRequest: HttpServletRequest): ResponseVO[Any] = {
    val opt = SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[Operator]
    if (opt.is_Role_Admin()) {
      val realOperator = opService.findByOpName(username)
      realOperator.setEnabled(enabled)
      opService.save(realOperator)
      return ResponseVO.success(opt)
    }
    return ResponseVO.failure("操作失败", None)
  }

}
