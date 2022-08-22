package org.apache.near.component.near;

/**
 * Producer operations (RPC calls to near node)
 * @author mabahma
 *
 */
public enum ProducerOperation {
	VALIDATORS,

	;

	/**
	 * findByName
	 * @param name : name to search
	 * @return: ProducerOperation
	 */
	public static ProducerOperation findByName(String name) {
		ProducerOperation result = null;
	    for (ProducerOperation operation : values()) {
	        if (operation.name().equalsIgnoreCase(name)) {
	            result = operation;
	            break;
	        }
	    }
	    return result;
	}
}
