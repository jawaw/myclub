package com.jawaw.myclub.service.impl

import java.util

import com.jawaw.myclub.dao.hibernate.CoachRepository
import com.jawaw.myclub.dao.mybatis.CoachMapper
import com.jawaw.myclub.model.{Coach, CoachMeta}
import com.jawaw.myclub.service.CoachService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CoachServiceImpl @Autowired() (val coachMapper: CoachMapper,
                                    val coachRepositiory: CoachRepository) extends CoachService{

  override def list(id: Int, coach_is_master: Int): util.List[CoachMeta] = {
    coachMapper.list(id, coach_is_master)
  }

  override def findCoachById(id: Int): Coach = {
    coachRepositiory.findById(id).get()
  }

  override def save(coach: Coach): Coach = {
    coachRepositiory.save(coach)
  }

  override def deleteCoachById(id: Int) = {
    coachRepositiory.deleteById(id)
  }
}
