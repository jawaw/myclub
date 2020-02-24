package com.jawaw.myclub.vo

import io.swagger.annotations.ApiModelProperty

import scala.beans.BeanProperty

class ResponseVO[T](@ApiModelProperty("状态码") @BeanProperty var code: Int,
                    @ApiModelProperty("接口返回数据") @BeanProperty var message: String,
                    @ApiModelProperty("接口返回数据") @BeanProperty var data: T) extends Serializable {

  val serialVersionUID = -4505655308965878999L
}

object ResponseVO extends ResponseStatus {

  def apply[T](code: Int, message: String, data: T): ResponseVO[T] = {
    new ResponseVO[T](code, message, data)
  }

  def success[T](data: T): ResponseVO[T] = {
    this(OK._1, OK._2, data)
  }

  def failure[T](message: String, data: T): ResponseVO[T] = {
    this(FAIL_4001._1, FAIL_4001._2, data)
  }

}

