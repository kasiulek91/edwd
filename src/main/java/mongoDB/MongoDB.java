package mongoDB;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;

import com.mongodb.DB;

import files.MyFile;

public class MongoDB {

	private MyFile myFile = null;
	private Mongo mongo = null;

	public static void main(String[] args) {
		MongoDB mongoDB = new MongoDB();

//		if (args.length < 1) {
//			System.out.println("U¿ycie MongoDB sciezka_do_pliku");
//			System.exit(0);
//		} else {
//			mongoDB.myFile = new MyFile(new File(args[0]),"usersTest","adsTest");
//		}

		try {
			System.out.println("connection");
			mongoDB.mongo = new Mongo();
		} catch (UnknownHostException e) {
			System.out
					.println("Problem with connection to MongoDB server." + e);
			System.exit(0);
		}

		// System.out.println("list all databases");
		// List<String> dbs = mongo.getAllDatabases();
		// for (String db : dbs) {
		// System.out.println("\t" + db);
		// }
		System.out.println("Connecting to database sito");
		DB db = mongoDB.mongo.conncectTo("sito");

		long startTime = System.currentTimeMillis();
		
//		 try {
//		 mongoDB.loadFile(db);
//		 } catch (IOException | ParseException e) {
//		 System.out.println("Blad przy ladowaniu bazy z pliku." + e);
//		 }
		MapReduce mapReduce = new MapReduce("description");
		mapReduce.doMapReduce(mongoDB.mongo.getCollection("ads_testing"));
		long estimatedTime = System.currentTimeMillis() - startTime;
		mapReduce.printResult();
		System.out.println("operacja trwa³a " + estimatedTime + " ms.");

	}

	public void loadFile(DB db) throws IOException, ParseException {
		myFile.loadColumnsfromFile();
		myFile.addFildLineToDB(db);
	}
}
