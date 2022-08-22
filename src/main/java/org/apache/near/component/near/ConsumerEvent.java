package org.apache.near.component.near;

/**
 * Consumer events
 * @author mabahma
 *
 */
public enum ConsumerEvent {
	DEPLOY_PROCESSED,
	
	/**
	 *  findByName
	 * @param name : name to search
	 * @return: ProducerOperation
	 */
	public static ConsumerEvent findByName(String name) {
		ConsumerEvent result = null;
	    for (ConsumerEvent operation : values()) {
	        if (operation.name().equalsIgnoreCase(name)) {
	            result = operation;
	            break;
	        }
	    }
	    return result;
	}
}
