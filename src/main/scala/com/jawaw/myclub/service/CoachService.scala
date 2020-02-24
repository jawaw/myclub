package com.jawaw.myclub.service

import com.jawaw.myclub.model.{Coach, CoachMeta}

trait CoachService {

  def list(id: Int, coach_is_master: Int): java.util.List[CoachMeta]

  def findCoachById(id: Int): Coach

  def save(coach: Coach): Coach

  def deleteCoachById(id: Int)



}
