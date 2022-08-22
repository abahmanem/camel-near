package org.apache.near.component.near;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.InvalidPathException;
import java.util.Arrays;

import org.apache.camel.Category;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.camel.support.DefaultEndpoint;
import org.apache.commons.validator.routines.UrlValidator;

import com.syntifi.near.api.service.NearService;

/**
 * Camel near endpoint : to interract with near nodes
 * 
 * @author mabahma
 *
 */

@UriEndpoint(firstVersion = "3.14.2", scheme = "near", title = "Near Camel Connector", syntax = "near:nodeUrl", label = "near", category = { Category.BITCOIN, Category.BLOCKCHAIN, Category.API })
public class NearEndPoint extends DefaultEndpoint {
	/**
	 * NearService bean : Near java SDK
	 */
	private NearService nearService;
	/**
	 * nodeUrl : node address
	 */
	@UriPath(description = "Node URL,  e.g. http://localhost:3030/ for producer, http://localhost:9999/events/main for consumer")
	@Metadata(required = true)
	private String nodeUrl;
	/**
	 * Near component configuration
	 */
	@UriParam(description = "near component configuration")
	private NearConfiguration configuration;

	/**
	 * CasperEndPoint constructor
	 *
	 * @param uri           : Node Uri
	 * @param remaining     : remaining
	 * @param nearComponent : nearComponent, either producer or consumer
	 * @param configuration : nearConfiguration
	 * @throws URISyntaxException : URISyntaxException
	 */
	public NearEndPoint(String uri, String remaining, NearComponent nearComponent, NearConfiguration configuration) throws URISyntaxException {
		super(uri, nearComponent);
		this.configuration = configuration;
		validateAndSetURL(remaining);
		// this.nodeUrl = remaining;
	}

	/**
	 * Create a Casper Consumer component
	 *
	 * @param processor : Apache Camel Processor
	 * @return CasperConsumer : Casper Consumer component
	 * @throws Exception : exception
	 */
	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		URI uri = new URI(nodeUrl);
		NearConsumer consumer = new NearConsumer(this, processor, configuration);
		configureConsumer(consumer);
		return consumer;

	}

	/**
	 * Create a Casper Producer component
	 * 
	 * @return CasperProducer : Casper Producer component
	 * @throws Exception : exception
	 */
	@Override
	public Producer createProducer() throws Exception {
		String operation = configuration.getOperationOrDefault();
		if (ProducerOperation.findByName(operation) != null)
			return new NearProducer(this, configuration);
		// Insupported operation
		throw new UnsupportedOperationException(String.format("Operation '%s' not supported by casper producer", operation));
	}

	@Override
	protected void doStart() throws Exception {
		if (configuration.getNearService() != null) {
			this.nearService = configuration.getNearService();
		} else {
			URI uri = new URI(nodeUrl);
			this.nearService = NearService.usingPeer(uri.getPath());
		}
		super.doStart();
	}

	/**
	 * Validate node Url
	 * 
	 * @param url : Casper Node url
	 * @throws URISyntaxException : uRISyntaxException
	 */
	public void validateAndSetURL(String url) throws URISyntaxException {
		UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
		if (urlValidator.isValid(url)) {
			setNodeUrl(new URI(url).toString());
		} else
			throw new InvalidPathException(url, "Provide a valid \"URL\" for node URL parameter. ");
	}

	public NearService getNearService() {
		return nearService;
	}

	public void setNearService(NearService nearService) {
		this.nearService = nearService;
	}

	public String getNodeUrl() {
		return nodeUrl;
	}

	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}

	public NearConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(NearConfiguration configuration) {
		this.configuration = configuration;
	}
}
