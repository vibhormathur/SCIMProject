
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/scala-workspace/SCIM-master/conf/routes
// @DATE:Fri May 19 23:16:55 CDT 2017

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

  // @LINE:8
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
  
    // @LINE:11
    def updateUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SCIMController.updateUser",
      """
        function(uid0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "scim/v2/Users/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("uid", encodeURIComponent(uid0))})
        }
      """
    )
  
    // @LINE:8
    def users: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SCIMController.users",
      """
        function(filter0,count1,startIndex2) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "scim/v2/Users" + _qS([(""" + implicitly[QueryStringBindable[Option[String]]].javascriptUnbind + """)("filter", filter0), (""" + implicitly[QueryStringBindable[Option[String]]].javascriptUnbind + """)("count", count1), (""" + implicitly[QueryStringBindable[Option[String]]].javascriptUnbind + """)("startIndex", startIndex2)])})
        }
      """
    )
  
    // @LINE:13
    def groups: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SCIMController.groups",
      """
        function(count0,startIndex1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "scim/v2/Groups" + _qS([(""" + implicitly[QueryStringBindable[Option[String]]].javascriptUnbind + """)("count", count0), (""" + implicitly[QueryStringBindable[Option[String]]].javascriptUnbind + """)("startIndex", startIndex1)])})
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
  
    // @LINE:10
    def createUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SCIMController.createUser",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "scim/v2/Users"})
        }
      """
    )
  
    // @LINE:15
    def patchGroup: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SCIMController.patchGroup",
      """
        function(groupId0) {
          return _wA({method:"PATCH", url:"""" + _prefix + { _defaultPrefix } + """" + "scim/v2/Groups/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("groupId", encodeURIComponent(groupId0))})
        }
      """
    )
  
    // @LINE:12
    def deleteUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.SCIMController.deleteUser",
      """
        function(uid0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "scim/v2/Users/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("uid", encodeURIComponent(uid0))})
        }
      """
    )
  
  }


}
