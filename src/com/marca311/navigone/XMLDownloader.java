package com.marca311.navigone;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;

import android.os.AsyncTask;

public class XMLDownloader extends AsyncTask<String, Integer, Document> {

	@Override
	protected Document doInBackground(String... params) {
		Document result = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			result = db.parse(new URL(params[0]).openStream());
		} catch (DOMException exception) {
			
		} catch (MalformedURLException e) {
			System.out.println("There is a problem with the URL");
			e.printStackTrace();
		}  catch (IOException e) {
			System.out.println("There was a problem retrieving the file from the server");
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			System.out.println("ParserConfigurationException");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Other Exception");
			e.printStackTrace();
		}
		
		return result;
	}
	@Override
	protected void onPostExecute(Document result) {
		
	}

}
