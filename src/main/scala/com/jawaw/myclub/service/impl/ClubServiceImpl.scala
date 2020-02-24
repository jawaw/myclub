package com.jawaw.myclub.service.impl

import java.util

import com.jawaw.myclub.dao.hibernate.ClubRepository
import com.jawaw.myclub.dao.mybatis.ClubMapper
import com.jawaw.myclub.model.Club
import com.jawaw.myclub.service.ClubService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class ClubServiceImpl @Autowired()(val clubMapper: ClubMapper,
                                   val clubRepository: ClubRepository) extends ClubService {

  override def list(id: Int) = {
      clubMapper.list(id)
  }

  override def createClub(club: Club) = {
    clubRepository.save(club)
  }

  override def findClubById(id: Int) = {
    clubRepository.findById(id).get()
  }

  override def updateClubById(club: Club) = {
    clubRepository.save(club)
  }

  override def deleteClubById(id: Int) = {
    clubRepository.deleteById(id)
  }

  override def searchClub(clubName: String, club_id: Int): util.List[Club] = {
    clubMapper.searchClubByName(clubName, club_id)
  }
}
