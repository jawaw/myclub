package com.jawaw.myclub.model

import java.util.Date

import com.fasterxml.jackson.annotation.{JsonFormat, JsonInclude}
import javax.persistence._

import scala.beans.BeanProperty


@Entity
@Table(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
class User {

  @Transient
  val serialVersionUID = 5199200306752426433L

  @Id
  @BeanProperty
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="user_id", columnDefinition = "int")
  var user_id: Int = _
  @BeanProperty
  @Column(name = "user_name", columnDefinition = "varchar(64)")
  var user_name: String = _
  @BeanProperty
  @Column(name = "user_gender", columnDefinition = "tinyint(1) default 0")
  var user_gender: Int = _
  @BeanProperty
  @Column(name = "user_desc", columnDefinition = "text")
  var user_desc: String = _
  @BeanProperty
  @Column(name ="user_pic", columnDefinition = "varchar(64)")
  var user_pic: String = _
  @BeanProperty
  @Column(name ="user_contact", columnDefinition = "varchar(64)")
  var user_contact: String = _
  @BeanProperty
  @Column(name = "create_time",columnDefinition = "datetime")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  var create_time : Date = _
  @BeanProperty
  @Column(name = "update_time",columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  var update_time : Date = _


}
