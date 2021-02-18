package factory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import factory.Item;

public abstract class Page {
	protected String title;
	protected ArrayList content = new ArrayList();

	public Page(String title) {
		this.title = title;
	}

	public void add(Item item) {
		content.add(item);
	}

	public void output() {
		try {
			String fileName = title + ".html";
			Writer writer = new FileWriter(fileName);
			writer.write(this.makeHTML());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract String makeHTML();
}
