package com.jawaw.myclub.model

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "menu_role")
@JsonInclude(JsonInclude.Include.NON_NULL)
class MenuRole extends Serializable {

  @Transient
  var serialVersionUID = 5199200306752426433L

  @Id
  @BeanProperty
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id", columnDefinition = "int")
  var id: Int = 0
  @BeanProperty
  @Column(name = "role_id", columnDefinition = "int")
  var role_id: Int = _
  @BeanProperty
  @Column(name = "menu_id", columnDefinition = "int")
  var menu_id: Int = _

}
