/**
 * 
 */
package org.cruxppc.cjg.witchboss;

/**
 * @author cjg
 *
 */
public class Witchboss {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Witchboss!");
		for(Device d : Device.getDevicesList())
			System.out.println(d);
		
	}

}
