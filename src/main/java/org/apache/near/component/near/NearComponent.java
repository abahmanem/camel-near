package org.apache.near.component.near;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.annotations.Component;
import org.apache.camel.support.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Component {@link CasperComponent}.
 */

/**
 * Camel CasperComponent
 * 
 * @author mabahma
 *
 */
@Component("casper")
public class NearComponent extends DefaultComponent {
	@Metadata(description = "Default configuration")
	private NearConfiguration configuration;
	public static final  Logger logger = LoggerFactory.getLogger(NearComponent.class);
	/**
	 * Create Casper endpoint
	 */
	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		NearConfiguration conf = configuration != null ? configuration.copy() : new NearConfiguration();
		NearEndPoint near = new NearEndPoint(uri, remaining, this, conf);
		setProperties(near, parameters);
		logger.debug("***** NearComponent create endpoint ");
		return near;
	}
	public NearConfiguration getConfiguration() {
		return configuration;
	}
	public void setConfiguration(NearConfiguration configuration) {
		this.configuration = configuration;
	}
}
