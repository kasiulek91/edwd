package mongoDB;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.List;

import files.MyFile;

public class MongoDB {

	public static void main(String[] args) {
		MyFile myFile = null;
		if (args.length < 1) {
			System.out.println("U¿ycie MongoDB sciezka_do_pliku");
			System.exit(0);
		} else {
			myFile = new MyFile(new File(args[0]));
		}

		Mongo mongo = null;
		try {
			System.out.println("connection");
			mongo = new Mongo();
		} catch (UnknownHostException e) {
			System.out
					.println("Problem with connection to MongoDB server." + e);
			System.exit(0);
		}

		System.out.println("list all databases");
		List<String> dbs = mongo.getAllDatabases();
		for (String db : dbs) {
			System.out.println("\t" + db);
		}
		mongo.conncectTo("sito");
		try {
			myFile.loadColumnsfromFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long startTime = System.currentTimeMillis();
		try {
			myFile.addFildLineToDB(mongo.getDb());
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("wrzucanie danyc trwa³o " + estimatedTime);

		// System.out.println("list all tables in sito");
		//
		//
		// for (DBCollection dbCollection : mongo.getTables()) {
		// System.out.println("\t" + dbCollection.getName());
		// /**** Find and display ****/
		// BasicDBObject searchQuery = new BasicDBObject();
		//
		// DBCursor cursor = dbCollection.find();
		//
		// while (cursor.hasNext()) {
		// System.out.println("\t\t" + cursor.next());
		// }
		// }
		// System.out.println("insert");
		// mongo.insertToTable("test2");
		// for (DBCollection dbCollection : mongo.getTables()) {
		// System.out.println("\t" + dbCollection.getName());
		// /**** Find and display ****/
		// BasicDBObject searchQuery = new BasicDBObject();
		//
		// DBCursor cursor = dbCollection.find();
		//
		// while (cursor.hasNext()) {
		// System.out.println("\t\t" + cursor.next());
		// }
		// }

	}
}
