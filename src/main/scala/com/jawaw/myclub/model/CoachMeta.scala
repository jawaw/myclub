package com.jawaw.myclub.model

import java.util.Date

import com.fasterxml.jackson.annotation.JsonFormat

import scala.beans.BeanProperty

class CoachMeta extends Serializable {

  @BeanProperty
  var coach_id: Int = _
  @BeanProperty
  var coach_name: String = _
  @BeanProperty
  var coach_gender: Int = _
  @BeanProperty
  var coach_desc: String = _
  @BeanProperty
  var coach_pic: String = _
  @BeanProperty
  var coach_is_master: Int = _
  @BeanProperty
  var club_id: Int = _
  @BeanProperty
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  var create_time : Date = _
  @BeanProperty
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  var update_time : Date = _
  @BeanProperty
  var club_name: String = _

}
