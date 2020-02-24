package com.jawaw.myclub.service.impl

import java.util.Date

import com.jawaw.myclub.dao.hibernate.OperatorReposotory
import com.jawaw.myclub.dao.mybatis.OperatorMapper
import com.jawaw.myclub.model.{Operator, OperatorMeta}
import com.jawaw.myclub.service.OpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.stereotype.Service

@Service
class OpServiceImpl @Autowired() (val operatorReposotory: OperatorReposotory,
                                   val operatorMapper: OperatorMapper) extends OpService{

  override def register(op: Operator): Operator = {
    op.setEnabled(true)
    op.setPassword(new Pbkdf2PasswordEncoder("hadiaOo0QdhaIlm8").encode(op.getPassword))
    op.setCreate_time(new Date(System.currentTimeMillis()))
    operatorReposotory.save(op)
  }

  override def opList(): java.util.List[OperatorMeta] = {
    operatorMapper.getAllOperator()
  }

  override def findByClubId(club_id: Int): Operator = {
    operatorMapper.findByClubId(club_id);
  }

  override def save(operator: Operator): Operator = {
    operatorReposotory.save(operator)
  }

  override def findByOpName(username: String): Operator = {
    operatorMapper.loadUserByUsername(username)
  }
}
