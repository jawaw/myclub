package com.jawaw.myclub.model

class MenuMeta extends Serializable {

  var keepAlive = false

  var requireAuth = false

  def isKeepAlive: Boolean = keepAlive
  def setKeepAlive(keepAlive: Boolean): Unit = {
    this.keepAlive = keepAlive
  }

  def isRequireAuth: Boolean = requireAuth
  def setRequireAuth(requireAuth: Boolean): Unit = {
    this.requireAuth = requireAuth
  }
}
