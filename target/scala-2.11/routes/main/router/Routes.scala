
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/scala-workspace/SCIM-master/conf/routes
// @DATE:Fri May 19 23:16:55 CDT 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_1: controllers.HomeController,
  // @LINE:8
  SCIMController_0: controllers.SCIMController,
  // @LINE:18
  Assets_2: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_1: controllers.HomeController,
    // @LINE:8
    SCIMController_0: controllers.SCIMController,
    // @LINE:18
    Assets_2: controllers.Assets
  ) = this(errorHandler, HomeController_1, SCIMController_0, Assets_2, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_1, SCIMController_0, Assets_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """scim/v2/Users""", """controllers.SCIMController.users(filter:Option[String], count:Option[String], startIndex:Option[String])"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """scim/v2/Users/""" + "$" + """uid<[^/]+>""", """controllers.SCIMController.user(uid:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """scim/v2/Users""", """controllers.SCIMController.createUser()"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """scim/v2/Users/""" + "$" + """uid<[^/]+>""", """controllers.SCIMController.updateUser(uid:String)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """scim/v2/Users/""" + "$" + """uid<[^/]+>""", """controllers.SCIMController.deleteUser(uid:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """scim/v2/Groups""", """controllers.SCIMController.groups(count:Option[String], startIndex:Option[String])"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """scim/v2/Groups/""" + "$" + """groupId<[^/]+>""", """controllers.SCIMController.group(groupId:String)"""),
    ("""PATCH""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """scim/v2/Groups/""" + "$" + """groupId<[^/]+>""", """controllers.SCIMController.patchGroup(groupId:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_1.index,
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      """ An example controller showing a sample home page""",
      this.prefix + """"""
    )
  )

  // @LINE:8
  private[this] lazy val controllers_SCIMController_users1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("scim/v2/Users")))
  )
  private[this] lazy val controllers_SCIMController_users1_invoker = createInvoker(
    SCIMController_0.users(fakeValue[Option[String]], fakeValue[Option[String]], fakeValue[Option[String]]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SCIMController",
      "users",
      Seq(classOf[Option[String]], classOf[Option[String]], classOf[Option[String]]),
      "GET",
      """""",
      this.prefix + """scim/v2/Users"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_SCIMController_user2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("scim/v2/Users/"), DynamicPart("uid", """[^/]+""",true)))
  )
  private[this] lazy val controllers_SCIMController_user2_invoker = createInvoker(
    SCIMController_0.user(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SCIMController",
      "user",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """scim/v2/Users/""" + "$" + """uid<[^/]+>"""
    )
  )

  // @LINE:10
  private[this] lazy val controllers_SCIMController_createUser3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("scim/v2/Users")))
  )
  private[this] lazy val controllers_SCIMController_createUser3_invoker = createInvoker(
    SCIMController_0.createUser(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SCIMController",
      "createUser",
      Nil,
      "POST",
      """""",
      this.prefix + """scim/v2/Users"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_SCIMController_updateUser4_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("scim/v2/Users/"), DynamicPart("uid", """[^/]+""",true)))
  )
  private[this] lazy val controllers_SCIMController_updateUser4_invoker = createInvoker(
    SCIMController_0.updateUser(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SCIMController",
      "updateUser",
      Seq(classOf[String]),
      "PUT",
      """""",
      this.prefix + """scim/v2/Users/""" + "$" + """uid<[^/]+>"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_SCIMController_deleteUser5_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("scim/v2/Users/"), DynamicPart("uid", """[^/]+""",true)))
  )
  private[this] lazy val controllers_SCIMController_deleteUser5_invoker = createInvoker(
    SCIMController_0.deleteUser(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SCIMController",
      "deleteUser",
      Seq(classOf[String]),
      "DELETE",
      """""",
      this.prefix + """scim/v2/Users/""" + "$" + """uid<[^/]+>"""
    )
  )

  // @LINE:13
  private[this] lazy val controllers_SCIMController_groups6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("scim/v2/Groups")))
  )
  private[this] lazy val controllers_SCIMController_groups6_invoker = createInvoker(
    SCIMController_0.groups(fakeValue[Option[String]], fakeValue[Option[String]]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SCIMController",
      "groups",
      Seq(classOf[Option[String]], classOf[Option[String]]),
      "GET",
      """""",
      this.prefix + """scim/v2/Groups"""
    )
  )

  // @LINE:14
  private[this] lazy val controllers_SCIMController_group7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("scim/v2/Groups/"), DynamicPart("groupId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_SCIMController_group7_invoker = createInvoker(
    SCIMController_0.group(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SCIMController",
      "group",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """scim/v2/Groups/""" + "$" + """groupId<[^/]+>"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_SCIMController_patchGroup8_route = Route("PATCH",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("scim/v2/Groups/"), DynamicPart("groupId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_SCIMController_patchGroup8_invoker = createInvoker(
    SCIMController_0.patchGroup(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SCIMController",
      "patchGroup",
      Seq(classOf[String]),
      "PATCH",
      """""",
      this.prefix + """scim/v2/Groups/""" + "$" + """groupId<[^/]+>"""
    )
  )

  // @LINE:18
  private[this] lazy val controllers_Assets_versioned9_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned9_invoker = createInvoker(
    Assets_2.versioned(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/""" + "$" + """file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_1.index)
      }
  
    // @LINE:8
    case controllers_SCIMController_users1_route(params) =>
      call(params.fromQuery[Option[String]]("filter", None), params.fromQuery[Option[String]]("count", None), params.fromQuery[Option[String]]("startIndex", None)) { (filter, count, startIndex) =>
        controllers_SCIMController_users1_invoker.call(SCIMController_0.users(filter, count, startIndex))
      }
  
    // @LINE:9
    case controllers_SCIMController_user2_route(params) =>
      call(params.fromPath[String]("uid", None)) { (uid) =>
        controllers_SCIMController_user2_invoker.call(SCIMController_0.user(uid))
      }
  
    // @LINE:10
    case controllers_SCIMController_createUser3_route(params) =>
      call { 
        controllers_SCIMController_createUser3_invoker.call(SCIMController_0.createUser())
      }
  
    // @LINE:11
    case controllers_SCIMController_updateUser4_route(params) =>
      call(params.fromPath[String]("uid", None)) { (uid) =>
        controllers_SCIMController_updateUser4_invoker.call(SCIMController_0.updateUser(uid))
      }
  
    // @LINE:12
    case controllers_SCIMController_deleteUser5_route(params) =>
      call(params.fromPath[String]("uid", None)) { (uid) =>
        controllers_SCIMController_deleteUser5_invoker.call(SCIMController_0.deleteUser(uid))
      }
  
    // @LINE:13
    case controllers_SCIMController_groups6_route(params) =>
      call(params.fromQuery[Option[String]]("count", None), params.fromQuery[Option[String]]("startIndex", None)) { (count, startIndex) =>
        controllers_SCIMController_groups6_invoker.call(SCIMController_0.groups(count, startIndex))
      }
  
    // @LINE:14
    case controllers_SCIMController_group7_route(params) =>
      call(params.fromPath[String]("groupId", None)) { (groupId) =>
        controllers_SCIMController_group7_invoker.call(SCIMController_0.group(groupId))
      }
  
    // @LINE:15
    case controllers_SCIMController_patchGroup8_route(params) =>
      call(params.fromPath[String]("groupId", None)) { (groupId) =>
        controllers_SCIMController_patchGroup8_invoker.call(SCIMController_0.patchGroup(groupId))
      }
  
    // @LINE:18
    case controllers_Assets_versioned9_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned9_invoker.call(Assets_2.versioned(path, file))
      }
  }
}
