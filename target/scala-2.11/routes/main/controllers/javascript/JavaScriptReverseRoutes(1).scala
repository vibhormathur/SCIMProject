
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/vmathu0/Downloads/SCIM-master/conf/routes
// @DATE:Fri May 19 09:11:14 CDT 2017

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:18
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:9
  class ReverseSCIMController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def user: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SCIMController.user",
      """
        function(uid0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "scim/v2/Users/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("uid", encodeURIComponent(uid0))})
        }
      """
    )
  
    // @LINE:10
    def createUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SCIMController.createUser",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "scim/v2/Users"})
        }
      """
    )
  
    // @LINE:14
    def group: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SCIMController.group",
      """
        function(groupId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "scim/v2/Groups/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("groupId", encodeURIComponent(groupId0))})
        }
      """
    )
  
  }


}
