/**
 * 
 */
package org.cruxppc.cjg.witchboss;

/**
 * @author cjg
 *
 */
public class Song extends Content {
	private Container artist;
	private Container album;
	private String filename;
	
	public Song(Container artist, Container album, String filename) {
		setContentStatus(ContentStatus.Unchanged);
		this.artist	 = artist;
		this.album = album;
		this.filename = filename;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Song [filename=" + filename + "]";
	}

	/* (non-Javadoc)
	 * @see org.cruxppc.cjg.witchboss.Content#commit()
	 */
	@Override
	public void commit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dump() {
		System.out.println(this);
	}

}
