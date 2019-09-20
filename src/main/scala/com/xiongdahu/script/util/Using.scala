package com.xiongdahu.script.util

/**
 * io资源读写管理
 */
object Using {

  def use[A <: {def close() : Unit}, R](resource: A)(fun: A => R): R = {
    try {
      fun(resource)
    } finally {
      resource.close()
    }
  }

}
