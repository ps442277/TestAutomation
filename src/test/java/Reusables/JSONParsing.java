package Reusables;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.*;
import org.json.simple.parser.*;

import Utility.Contants;

public class JSONParsing {
	/*
	 * The class is used for parsing the JSON file by both reading and writing the value in Json file.
	 */
	JSONParser jparser = new JSONParser();
	JSONObject jobject;
	FileWriter writer;
	String filepath = System.getProperty("user.dir")+Contants.JSON_path;
	FileReader reader;
	//Reading data from JSON data file using FileReader and JSONObject by parsing the value.
	public String read_json(String data) throws IOException, ParseException {
		reader = new FileReader(filepath);
		jobject = (JSONObject) jparser.parse(reader);
		return (String)jobject.get(data);
	}
	//Writing data into JSON data file by using FileWriter and JSONObject with Key value method.
	public void write_json(String key, String value) throws IOException, ParseException {
		reader = new FileReader(filepath);
		jobject = (JSONObject) jparser.parse(reader);
		writer = new FileWriter(filepath);
		jobject.put(key, value);
		writer.write(jobject.toString());
		writer.flush();
	}
}
