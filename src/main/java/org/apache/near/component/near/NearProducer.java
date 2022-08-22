package org.apache.near.component.near;

import java.io.IOException;
import java.util.Arrays;

import org.apache.camel.CamelExchangeException;
import org.apache.camel.Message;
import org.apache.camel.spi.InvokeOnHeader;
import org.apache.camel.support.HeaderSelectorProducer;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;

import com.syntifi.near.api.service.NearService;

/**
 * Camel CasperProducer component
 * 
 * @author mabahma
 *
 */
@SuppressWarnings("unused")
public class NearProducer extends HeaderSelectorProducer {

	/**
	 * Camel Casper endpoint
	 */
	private final NearEndPoint endpoint;
	/**
	 * Near Java Node API
	 */
	private final NearService nearService;

	/**
	 * Casper Camel configuration
	 */
	private final NearConfiguration configuration;

	/**
	 * CasperProducer constructor
	 * 
	 * @param endpoint      : near endpoint
	 * @param configuration : nearConfiguration
	 */
	public NearProducer(NearEndPoint endpoint, final NearConfiguration configuration) {
		super(endpoint, NearConstants.OPERATION, () -> configuration.getOperationOrDefault(), false);
		this.endpoint = endpoint;
		this.configuration = configuration;
		this.nearService = endpoint.getNearService();
	}

	@Override
	public NearEndPoint getEndpoint() {
		return (NearEndPoint) super.getEndpoint();
	}

	/**
	 * Call to validators method
	 *
	 * @param message
	 * @throws IOException
	 */
	@InvokeOnHeader(NearConstants.VALIDATORS)
	void validators(Message message) {
		
	}

	
	public NearService getNearService() {
		return nearService;
	}

	public NearConfiguration getConfiguration() {
		return configuration;
	}

}
