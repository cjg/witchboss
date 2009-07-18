/**
 * 
 */
package org.cruxppc.cjg.witchboss;

/**
 * @author cjg
 *
 */
public abstract class Content {
	private ContentStatus status;
	
	public ContentStatus getContentStatus() {
		return status;
	}
	
	public void setContentStatus(ContentStatus status) {
		this.status = status;
	}
	
	public abstract void commit();
	public abstract void dump();
}
