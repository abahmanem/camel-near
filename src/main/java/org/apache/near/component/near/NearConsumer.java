package org.apache.near.component.near;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import org.apache.camel.Processor;
import org.apache.camel.ShutdownRunningTask;
import org.apache.camel.Suspendable;
import org.apache.camel.spi.ShutdownAware;
import org.apache.camel.support.DefaultConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

/**
 * Camel CasperComsumer Component
 * 
 * @author mabahma
 *
 */

public class NearConsumer extends DefaultConsumer implements ShutdownAware, Suspendable {
	public static final Logger logger = LoggerFactory.getLogger(NearConsumer.class);
	private final NearEndPoint endpoint;
	private final NearConfiguration configuration;
	private volatile boolean shutdownPending=false;
	private volatile boolean forceShutdown=false;
	/**
	 * Create Casper consumer component
	 * 
	 * @param endpoint      : endpoint
	 * @param processor     : consumer Processor
	 * @param configuration : CasperConfiguration
	 */
	public NearConsumer(NearEndPoint endpoint, Processor processor, NearConfiguration configuration) {
		super(endpoint, processor);
		this.configuration = configuration;
		this.endpoint = endpoint;
	}
	@Override
	protected void doStart() throws Exception {
		super.doStart();
		EventHandler eventHandler = new NearEventHandler(this);
		EventSource builder = new EventSource.Builder(eventHandler, new URI(endpoint.getNodeUrl())).reconnectTime(Duration.ofMillis(3000)).build();
		builder.start();
	}
	@Override
	public boolean deferShutdown(ShutdownRunningTask shutdownRunningTask) {
		return true;
	}
	@Override
	public int getPendingExchangesSize() {
		return 0;
	}
	@Override
	public void prepareShutdown(boolean suspendOnly, boolean forced) {
		// if we are suspending then we want to keep the thread running but just not
		// route the exchange
		// this logic is only when we stop or shutdown the consumer
		if (suspendOnly) {
			logger.debug("Skip preparing to shutdown as consumer is being suspended");
			return;
		}
		// signal we want to shutdown
		shutdownPending = true;
		forceShutdown = forced;
	}
	@Override
	public NearEndPoint getEndpoint() {
		return endpoint;
	}
	public NearConfiguration getConfiguration() {
		return configuration;
	}
}
