package com.jawaw.myclub.model

import java.util.Date

import com.fasterxml.jackson.annotation.JsonFormat
import javax.persistence.Transient

import scala.beans.BeanProperty

class OperatorMeta extends Serializable {

  @Transient
  val serialVersionUID = 5199200306752426433L

  var id: String = _
  @BeanProperty
  var username: String = _
  @BeanProperty
  var club_id: Int = _
  @BeanProperty
  var locked: Boolean = _
  @BeanProperty
  var enabled: Boolean = _
  @BeanProperty
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  var create_time : Date = _
  @BeanProperty
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  var update_time : Date = _
  @BeanProperty
  var club_name: String = _
}
