package com.jc.io.xml;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;
/**
 * 对象序列化也只能局限于Java中，跨语言传输数据，还是得靠与语言无关的格式，如xml
 * @author jevoncode
 *
 */
public class Person {
	private String first, last;

	public Person(String first, String last) {
		this.first = first;
		this.last = last;
	}

	// Produce an XML Element from this Person object:
	public Element getXML() {
		Element person = new Element("person");
		Element firstName = new Element("first");
		firstName.appendChild(first);
		Element lastName = new Element("last");
		lastName.appendChild(last);
		person.appendChild(firstName);
		person.appendChild(lastName);
		return person;
	}

	// Constructor to restore a Person from an XML Element:
	public Person(Element person) {
		first = person.getFirstChildElement("first").getValue();
		last = person.getFirstChildElement("last").getValue();
	}

	public String toString() {
		return first + " " + last;
	}

	// Make it human-readable:
	public static void format(OutputStream os, Document doc) throws Exception {
		Serializer serializer = new Serializer(os, "UTF8"); //ISO-8859-1支持中文，需指定编码格式
		serializer.setIndent(4);
		serializer.setMaxLength(60);
		serializer.write(doc);
		serializer.flush();
	}

	public static void main(String[] args) throws Exception {
		List<Person> people = Arrays.asList(new Person("Dr. Bunsen", "Honeydew"), new Person("Gonzo", "The Great"),
				new Person("Phillip J.", "Fry"),
				new Person("萧炎", "斗帝"));
		System.out.println(people);
		Element root = new Element("people");
		for (Person p : people)
			root.appendChild(p.getXML());
		Document doc = new Document(root);
		format(System.out, doc);
		format(new BufferedOutputStream(new FileOutputStream("People.xml")), doc);
	}
}