package org.apache.camel.component.casper;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;

public class NearTestSupport extends CamelTestSupport {

	@EndpointInject("mock:result")
	protected MockEndpoint mockResult;

	@EndpointInject("mock:error")
	protected MockEndpoint mockError;

	@Override
	public boolean isUseAdviceWith() {
		return true;
	}


}
