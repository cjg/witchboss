/**
 * 
 */
package org.cruxppc.cjg.witchboss;

import java.io.File;
import java.util.Vector;

/**
 * @author cjg
 *
 */
public class Container extends Content {
	private String path;
	private ContainerType type;
	private Vector<Content> content;
	private Container parent;
	
	public Container(String path, ContainerType type, Container parent) {
		this.path = path;
		this.type = type;
		setContentStatus(ContentStatus.Unchanged);
		this.parent = parent;
		content = new Vector<Content>();
		switch(type) {
		case Music:
			initMusicContent();
			break;
		case Artist:
			initArtistContent();
			break;
		case Album:
			initAlbumContent();
			break;
		}
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		
	}
	
	private void initMusicContent() {
		// so my contents are Artists
		File d = new File(path); 
		for(String i : d.list()) {
			File f = new File(path + File.separatorChar + i);
			if(!f.isDirectory())
				continue;
			content.add(new Container(path + File.separatorChar + i, ContainerType.Artist, parent));
		}
	}

	private void initArtistContent() {
		// so my contents are Albums
		File d = new File(path); 
		for(String i : d.list()) {
			File f = new File(path + File.separatorChar + i);
			if(!f.isDirectory())
				continue;
			content.add(new Container(path + File.separatorChar + i, ContainerType.Album, parent));
		}
	}

	private void initAlbumContent() {
		// so my contents are Songs
		File d = new File(path); 
		for(String i : d.list()) {
			if(!i.toLowerCase().endsWith(".mp3"))
				continue;
			File f = new File(path + File.separatorChar + i);
			if(!f.isFile())
				continue;
			content.add(new Song(parent, this, i));
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Container [type=" + type + "]";
	}

	public void dump() {
		System.out.println(this);
		for(Content t : content)
			t.dump();
	}
}
