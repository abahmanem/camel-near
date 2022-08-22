package org.apache.near.component.near;

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;

import com.syntifi.near.api.service.NearService;

/**
 * Camel Near endpoint configuration
 * 
 * @author mabahma
 *
 */
@UriParams
public class NearConfiguration implements Cloneable {

	@UriParam(label = "producer", defaultValue = NearConstants.VALIDATORS, description = "The endpoint operation.", enums = NearConstants.ENDPOINT_SERVICE)
	private String operation;

	
	/**
	 * NearService : Near RPC SDK
	 */
	@UriParam(label = "common", description = "Near RPC API used to perform RPC queries on Near Network Blockchain")
	private NearService nearService;
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public NearService getNearService() {
		return nearService;
	}

	public void setNearService(NearService nearService) {
		this.nearService = nearService;
	}

	public String getOperationOrDefault() {
		return this.operation != null ? operation : NearConstants.VALIDATORS;
	}
	
	public NearConfiguration copy() {
		try {
			return (NearConfiguration) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeCamelException(e);
		}
	}
}
