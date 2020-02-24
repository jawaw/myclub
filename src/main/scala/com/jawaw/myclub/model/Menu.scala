package com.jawaw.myclub.model

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence._

import scala.beans.BeanProperty

@Entity
@Table(name = "menu")
@JsonInclude(JsonInclude.Include.NON_NULL)
class Menu extends Serializable {

  @Transient
  var serialVersionUID = 5199200306752426433L

  @Id
  @BeanProperty
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id", columnDefinition = "int")
  var id: Int = 0
  @BeanProperty
  @Column(name = "url", unique=true, columnDefinition = "varchar(64)")
  var url: String = _
  @BeanProperty
  @Column(name = "path", unique=true, columnDefinition = "varchar(64)")
  var path: String = _
  @BeanProperty
  @Column(name = "component", unique=true, columnDefinition = "varchar(64)")
  var component: String = _
  @BeanProperty
  @Column(name = "name", unique=true, columnDefinition = "varchar(64)")
  var name: String = _
  @BeanProperty
  @Column(name = "iconCls", unique=true, columnDefinition = "varchar(64)")
  var iconCls: String = _
  @BeanProperty
  @Column(name = "keepAlive", unique=true, columnDefinition = "tinyint(1)")
  var keepAlive: Boolean = _
  @BeanProperty
  @Column(name = "requireAuth", unique=true, columnDefinition = "tinyint(1)")
  var requireAuth: Boolean = _
  @BeanProperty
  @Column(name = "parentId", unique=true, columnDefinition = "int")
  var parentId: Int = _
  @BeanProperty
  @Column(name = "enabled", unique=true, columnDefinition = "tinyint(1) DEFAULT '1'")
  var enabled: Boolean = _
  @Transient
  @BeanProperty
  var roles: String = _
  @Transient
  @BeanProperty
  var children: java.util.List[Menu] = _
  @Transient
  @BeanProperty
  var meta: MenuMeta = _

}
