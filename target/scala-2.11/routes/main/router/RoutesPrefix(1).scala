
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/vmathu0/Downloads/SCIM-master/conf/routes
// @DATE:Fri May 19 09:11:14 CDT 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
