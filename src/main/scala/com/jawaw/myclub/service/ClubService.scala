package com.jawaw.myclub.service

import com.jawaw.myclub.model.Club

trait ClubService {

  def list(id: Int): java.util.List[Club]

  def searchClub(clubName: String, club_id: Int): java.util.List[Club]

  def findClubById(id: Int): Club

  def createClub(club: Club): Unit

  def updateClubById(club: Club): Unit

  def deleteClubById(id: Int): Unit


}
