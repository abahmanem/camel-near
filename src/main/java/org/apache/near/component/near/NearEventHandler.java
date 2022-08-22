package org.apache.near.component.near;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

/**
 * Event handler for the consumer
 * 
 * @author mabahma
 *
 */
public class NearEventHandler implements EventHandler {

	public static final Logger logger = LoggerFactory.getLogger(NearEventHandler.class);
	private final NearConsumer consumer;
	private final NearEndPoint endpoint;

	public NearEventHandler(NearConsumer consumer) {
		super();
		this.consumer = consumer;
		this.endpoint = this.consumer.getEndpoint();
	}

	@Override
	public void onOpen() throws Exception {
		logger.info("The event stream has been opened");
	}

	@Override
	public void onClosed() throws Exception {
		logger.info("The event stream has been closed");
	}

	/**
	 * 
	 */
	@Override
	public void onMessage(String evt, MessageEvent messageEvent) throws Exception {
		
	}

	@Override
	public void onComment(String comment) throws Exception {
		logger.info("Received a comment line from the stream");
	}

	@Override
	public void onError(Throwable t) {
		logger.error("an error occured when connecting to the event stream", t);
	}

	
}
