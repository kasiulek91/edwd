package mongoDB;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Mongo {
	private MongoClient mongo;
	private DB db;
	private Set<DBCollection> tables;

	public Mongo() throws UnknownHostException {
		mongo = new MongoClient("localhost", 27017);
		tables = new HashSet<DBCollection>();
	}

	public List<String> getAllDatabases() {
		return mongo.getDatabaseNames();
	}

	public DB conncectTo(String databaseName) {
		db = mongo.getDB(databaseName);
		return db;
	}

	private void initTables() {
		Set<String> tablesNames = db.getCollectionNames();

		for (String coll : tablesNames) {
			DBCollection table = db.getCollection(coll);
			tables.add(table);
		}
	}

	public DBCollection getCollection(String name) {
		return db.getCollection(name);
	}

	public Set<DBCollection> getTables() {
		if (tables.size() < db.getCollectionNames().size()) {
			initTables();
		}
		return tables;
	}

	public DB getDb() {
		return db;
	}

}
