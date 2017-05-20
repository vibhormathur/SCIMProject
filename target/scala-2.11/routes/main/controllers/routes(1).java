
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/vmathu0/Downloads/SCIM-master/conf/routes
// @DATE:Fri May 19 09:11:14 CDT 2017

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseSCIMController SCIMController = new controllers.ReverseSCIMController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseSCIMController SCIMController = new controllers.javascript.ReverseSCIMController(RoutesPrefix.byNamePrefix());
  }

}
