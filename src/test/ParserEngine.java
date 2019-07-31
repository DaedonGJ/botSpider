package test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserEngine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			listAllLinks(args[0]);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void listAllLinks(String url) throws IOException {
		System.out.println("Parsing page " + url + "...");

		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("a[href]");
		Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");

		System.out.println("los links son ");
		links.stream().forEach((Object o)->crawUrl((Element)o));
	

		System.out.println("los medios son ");
		media.stream().forEach((Object o)->System.out.println(o));
		
		System.out.println("los imports son ");
		imports.stream().forEach((Object o)->System.out.println(o));
	}
	public static void crawUrl(Element path) {
		String ruta =path.absUrl("href");
		try {
			listAllLinks(ruta);
		} catch (IOException e) {
	
		}
		System.out.println(ruta);
	}
}
