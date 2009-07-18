/**
 * 
 */
package org.cruxppc.cjg.witchboss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Vector;

/**
 * @author cjg
 *
 */
public class Device {
	private static Vector<Device> devices = null;
	private String mountpoint;
	private String properties_file;
	private Properties properties;
	private Container music;
	
	private Device(String mountpoint) {
		this.mountpoint = mountpoint;
		properties_file = mountpoint + File.separatorChar + ".witchboss_device";
		properties = new Properties();
		File f = new File(properties_file);
		if(f.length() == 0)
			try {
				initPropertiesFile();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			properties.loadFromXML(new FileInputStream(properties_file));
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String music_path = mountpoint + File.separatorChar + properties.getProperty("music_directory", "Music");
		File d = new File(music_path);
		if(!d.isDirectory())
			d.mkdir();
		music = new Container(music_path, ContainerType.Music, null);
		music.dump();
	}
	
	public String getName() {
		return properties.getProperty("name");
	}
	
	public void setName(String name) {
		properties.put("name", name);
		try {
			properties.storeToXML(new FileOutputStream(properties_file), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Device [mountpoint=" + mountpoint + ", name=\"" + properties.getProperty("name") + "\"]";
	}

	public static Vector<Device> getDevicesList() {
		if(devices == null)
			initDevicesList();
		return devices;
	}
	
	private static void initDevicesList() {
		devices = new Vector<Device>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("/proc/mounts"));
			String line;
			while((line = br.readLine()) != null) {
				String[] splitted = line.split(" ");
				File f = new File(splitted[1] + File.separatorChar + ".witchboss_device");
				if(f.exists())
					devices.add(new Device(splitted[1]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initPropertiesFile() throws FileNotFoundException, IOException {
		properties.put("name", "Witchboss Device on " + mountpoint);
		properties.put("music_directory", "Music");
		properties.storeToXML(new FileOutputStream(properties_file), null);
	}
}
