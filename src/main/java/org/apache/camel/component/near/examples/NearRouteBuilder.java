package org.apache.camel.component.near.examples;

import org.apache.camel.builder.RouteBuilder;
import org.apache.near.component.near.NearConstants;

/**
 * CasperRouteBuilder 
 * @author mabahma
 *
 */

public class NearRouteBuilder extends RouteBuilder {

	public void configure()
   {
     
	 	   //example routes for producer  using a testnet node
	   
	   
	   from("timer://simpleTimer?period=3000")
	      .to("near:http://65.21.227.180:3030/?operation="+NearConstants.VALIDATORS)
	      .log("call "+NearConstants.VALIDATORS +"gives result = - ${body}");
	 
   }
}
