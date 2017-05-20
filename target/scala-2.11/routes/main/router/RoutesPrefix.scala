
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/scala-workspace/SCIM-master/conf/routes
// @DATE:Fri May 19 23:16:55 CDT 2017


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
