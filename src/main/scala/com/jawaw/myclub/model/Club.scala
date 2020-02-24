package com.jawaw.myclub.model

/**
  "int"
  "varchar"
  "text"
  "mediumtext"
  "longtext"
  "datetime"
  "decimal"
  "double"
  "char"
  "bigint"
  "bit"
  "timestamp"
  "date"
  "time"
  "float"

  **/

import java.util.Date

import com.fasterxml.jackson.annotation.{JsonFormat, JsonInclude}
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "club")
@JsonInclude(JsonInclude.Include.NON_NULL)
class Club extends Serializable {

  @Transient
  val serialVersionUID = 5199200306752426433L

//  @Transient
//  var file: MultipartFile = _

  @Id
  @BeanProperty
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="club_id", columnDefinition = "int")
  var club_id: Int = _
  @BeanProperty
  @Column(name = "club_name", columnDefinition = "varchar(40)")
  var club_name: String = _
  @BeanProperty
  @Column(name = "club_desc", columnDefinition = "text")
  var club_desc: String = _
  @BeanProperty
  @Column(name ="club_contact", columnDefinition = "varchar(40)")
  var club_contact: String = _
  @BeanProperty
  @Column(name ="club_address", columnDefinition = "varchar(40)")
  var club_address: String = _
  @BeanProperty
  @Column(name ="club_pic", columnDefinition = "varchar(255)")
  var club_pic: String = _
  @BeanProperty
  @Column(name ="club_manager", columnDefinition = "varchar(40)")
  var club_manager: String = _
  @BeanProperty
  @Column(name ="club_status", columnDefinition = "tinyint(4)")
  var club_status: Int = _
  @BeanProperty
  @Column(name = "create_time",columnDefinition = "datetime")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  var create_time : Date = _
  @BeanProperty
  @Column(name = "update_time",columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  var update_time : Date = _

}
