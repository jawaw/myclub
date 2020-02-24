package com.jawaw.myclub.model

import java.util
import java.util.Date

import com.fasterxml.jackson.annotation.{JsonFormat, JsonInclude, JsonProperty}
import javax.persistence._
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import scala.beans.BeanProperty

@Entity
@Table(name = "operator")
@JsonInclude(JsonInclude.Include.NON_NULL)
class Operator extends UserDetails{

  @Transient
  val serialVersionUID = 5199200306752426433L

  @Id
  @BeanProperty
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id", columnDefinition = "int")
  var id: String = _
  @BeanProperty
  @Column(name = "username", unique=true, columnDefinition = "varchar(40)")
  var username: String = _
  @BeanProperty
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Column(name = "password", columnDefinition = "varchar(255)")
  var password: String = _
  @BeanProperty
  @Column(name="club_id", columnDefinition = "int")
  var club_id: Int = _
  @BeanProperty
  @Column(name="locked", columnDefinition = "tinyint(1)")
  var locked: Boolean = _
  @BeanProperty
  @Column(name="enabled", columnDefinition = "tinyint(1)")
  var enabled: Boolean = _
  @BeanProperty
  @Column(name = "create_time",columnDefinition = "datetime")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  var create_time : Date = _
  @BeanProperty
  @Column(name = "update_time",columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  var update_time : Date = _

  def is_Role_Admin(): Boolean = {
    val authorities = getAuthorities
    if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
        return true;
    return false;
  }

  def is_Role_User(coach: Coach): Boolean = {
    return this.club_id == coach.club_id
  }

  override def getAuthorities: util.Collection[_ <: GrantedAuthority] = {
    val authorityList = new util.ArrayList[GrantedAuthority]()
    if("admin".equals(username))
      authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"))
    authorityList.add(new SimpleGrantedAuthority("ROLE_USER"))
    authorityList
  }

  override def isAccountNonExpired: Boolean = true

  override def isAccountNonLocked: Boolean = !locked

  override def isCredentialsNonExpired: Boolean = true

  override def isEnabled: Boolean = enabled
}
