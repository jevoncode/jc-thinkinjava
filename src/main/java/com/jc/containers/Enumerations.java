package com.jc.containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

import com.jc.designpattern.containers.Countries;

/**
 * 历史遗留的Enumeration
 * @author jevoncode
 *
 */
public class Enumerations {
	public static void main(String[] args) {
		Vector<String> v = new Vector<String>(Countries.names(10));
		Enumeration<String> e = v.elements(); //类是于List的iterator()
		while (e.hasMoreElements())
			System.out.print(e.nextElement() + ", ");
		// Produce an Enumeration from a Collection:
		e = Collections.enumeration(new ArrayList<String>());
	}
}