package com.jawaw.myclub.model

import java.util.Date

import com.fasterxml.jackson.annotation.{JsonFormat, JsonInclude}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "coach")
@JsonInclude(JsonInclude.Include.NON_NULL)
class Coach extends Serializable {

  @Transient
  val serialVersionUID = 5199200306752426433L

  @Id
  @BeanProperty
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="coach_id", columnDefinition = "int")
  var coach_id: Int = _
  @BeanProperty
  @Column(name = "coach_name", columnDefinition = "varchar(40)")
  var coach_name: String = _
  @BeanProperty
  @Column(name = "coach_gender", columnDefinition = "tinyint(1)")
  var coach_gender: Int = _
  @BeanProperty
  @Column(name = "coach_desc", columnDefinition = "text")
  var coach_desc: String = _
  @BeanProperty
  @Column(name ="coach_pic", columnDefinition = "varchar(40)")
  var coach_pic: String = _
  @BeanProperty
  @Column(name ="coach_is_master", columnDefinition = "tinyint(4)")
  var coach_is_master: Int = _
  @BeanProperty
  @Column(name ="club_id", columnDefinition = "int")
  var club_id: Int = _
  @BeanProperty
  @Column(name = "create_time",columnDefinition = "datetime")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  var create_time : Date = _
  @BeanProperty
  @Column(name = "update_time",columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  var update_time : Date = _

}
