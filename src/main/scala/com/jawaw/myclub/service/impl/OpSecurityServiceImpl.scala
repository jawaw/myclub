package com.jawaw.myclub.service.impl

import com.jawaw.myclub.dao.mybatis.OperatorMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.{UserDetails, UserDetailsService, UsernameNotFoundException}
import org.springframework.stereotype.Service

@Service
class OpSecurityServiceImpl @Autowired()(val operatorMapper: OperatorMapper) extends UserDetailsService {


  @throws[UsernameNotFoundException]
  override def loadUserByUsername(username: String): UserDetails = {
    operatorMapper.loadUserByUsername(username)
  }

}