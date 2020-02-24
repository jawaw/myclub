package com.jawaw.myclub.service

import com.jawaw.myclub.model.{Operator, OperatorMeta}

trait OpService {

  def register(op: Operator): Operator

  def opList(): java.util.List[OperatorMeta]

  def findByClubId(club_id: Int): Operator

  def findByOpName(username: String): Operator

  def save(operator: Operator): Operator

}
