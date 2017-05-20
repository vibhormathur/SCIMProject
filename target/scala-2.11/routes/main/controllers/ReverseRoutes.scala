
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/scala-workspace/SCIM-master/conf/routes
// @DATE:Fri May 19 23:16:55 CDT 2017

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix)
    }
  
  }

  // @LINE:18
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:18
    def versioned(file:Asset): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:8
  class ReverseSCIMController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def user(uid:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "scim/v2/Users/" + implicitly[PathBindable[String]].unbind("uid", dynamicString(uid)))
    }
  
    // @LINE:11
    def updateUser(uid:String): Call = {
      import ReverseRouteContext.empty
      Call("PUT", _prefix + { _defaultPrefix } + "scim/v2/Users/" + implicitly[PathBindable[String]].unbind("uid", dynamicString(uid)))
    }
  
    // @LINE:8
    def users(filter:Option[String], count:Option[String], startIndex:Option[String]): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "scim/v2/Users" + queryString(List(Some(implicitly[QueryStringBindable[Option[String]]].unbind("filter", filter)), Some(implicitly[QueryStringBindable[Option[String]]].unbind("count", count)), Some(implicitly[QueryStringBindable[Option[String]]].unbind("startIndex", startIndex)))))
    }
  
    // @LINE:13
    def groups(count:Option[String], startIndex:Option[String]): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "scim/v2/Groups" + queryString(List(Some(implicitly[QueryStringBindable[Option[String]]].unbind("count", count)), Some(implicitly[QueryStringBindable[Option[String]]].unbind("startIndex", startIndex)))))
    }
  
    // @LINE:14
    def group(groupId:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "scim/v2/Groups/" + implicitly[PathBindable[String]].unbind("groupId", dynamicString(groupId)))
    }
  
    // @LINE:10
    def createUser(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "scim/v2/Users")
    }
  
    // @LINE:15
    def patchGroup(groupId:String): Call = {
      import ReverseRouteContext.empty
      Call("PATCH", _prefix + { _defaultPrefix } + "scim/v2/Groups/" + implicitly[PathBindable[String]].unbind("groupId", dynamicString(groupId)))
    }
  
    // @LINE:12
    def deleteUser(uid:String): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "scim/v2/Users/" + implicitly[PathBindable[String]].unbind("uid", dynamicString(uid)))
    }
  
  }


}
