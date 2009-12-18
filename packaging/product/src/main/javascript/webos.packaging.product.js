eXo.require("eXo.projects.Module") ;
eXo.require("eXo.projects.Product") ;

function getProduct(version) {
  var product = new Product();
  
  product.name = "eXoWebOS" ;
  product.portalwar = "portal.war" ;
  product.codeRepo = "webos" ;
  product.useWorkflow = false;
  product.serverPluginVersion = "${org.exoplatform.portal.version}" ;
    
  var kernel = Module.GetModule("kernel") ;
  var core = Module.GetModule("core") ;
  var ws = Module.GetModule("ws");
  var eXoPortletContainer = Module.GetModule("portletcontainer", {kernel : kernel, core : core}) ;    
  var eXoJcr = Module.GetModule("jcr", {kernel : kernel, core : core, ws : ws}) ;
  var portal = Module.GetModule("portal", {kernel : kernel, ws:ws, core : core, eXoPortletContainer : eXoPortletContainer, eXoJcr : eXoJcr});
  var webos = Module.GetModule("webos", {kernel : kernel, core : core, eXoPortletContainer : eXoPortletContainer, eXoJcr : eXoJcr });
  
  product.addDependencies(portal.web.rest) ;
  product.addDependencies(portal.portlet.exoadmin) ;
  product.addDependencies(portal.portlet.web) ;
  product.addDependencies(portal.portlet.dashboard) ;
	product.addDependencies(portal.eXoGadgetServer) ;
	product.addDependencies(portal.eXoGadgets) ;
  product.addDependencies(portal.webui.portal);
  
	product.addDependencies(portal.web.eXoResources);
	product.addDependencies(portal.web.eXoMacSkin);
	product.addDependencies(portal.web.eXoVistaSkin);
	product.addDependencies(webos.web.webosResources);
	
  product.addDependencies(webos.web.webosportal) ;
  
  product.addServerPatch("tomcat", portal.server.tomcat.patch) ;
  product.addServerPatch("jboss",  portal.server.jboss.patch) ;
  product.addServerPatch("jbossear",  portal.server.jbossear.patch) ;
  product.addServerPatch("jonas",  portal.server.jonas.patch) ;
  product.addServerPatch("ear",  portal.server.websphere.patch) ;
  
  product.module = webos ;
  product.dependencyModule = [kernel, core, eXoPortletContainer, ws, eXoJcr, portal];
    
  return product ;
}
