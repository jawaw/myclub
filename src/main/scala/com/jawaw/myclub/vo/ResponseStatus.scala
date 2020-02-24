package com.jawaw.myclub.vo


trait ResponseStatus {

  /**
    *  请求成功状态码
    */
  val OK = (2000, "success")
  /**
    * 请求失败
    */
  val FAIL_4000 = (4000, "FAIL_4000")
  /**
    *  未认证
    */
  val FAIL_4001 = (4001, "FAIL_4001")
  /**
    * 无权限
    */
  val FAIL_4003 = (4003, "FAIL_4003")


}

