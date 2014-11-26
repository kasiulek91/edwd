package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import tables.Ad;
import tables.User;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class MyFile {
	private File file;
	private String usersTableName;
	private String adsTableName;
	private BufferedReader bufferedReader;
	private List<String> columns;
	private Charset ch = Charset.forName("UTF-8");

	public MyFile(File file, String usersTableName, String adsTableName) {
		this.file = file;
		this.usersTableName = usersTableName;
		this.adsTableName = adsTableName;
	}

	public List<String> getColumns() {
		return columns;
	}

	private void openFile() throws FileNotFoundException {
		bufferedReader = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), ch));
	}

	private void closeFile() throws IOException {
		bufferedReader.close();
	}

	/**
	 * Metoda odycztuj¹ca kolumny z pliku
	 * 
	 * @throws IOException
	 */
	public void loadColumnsfromFile() throws IOException {
		openFile();
		columns = new ArrayList<String>();
		String line = bufferedReader.readLine();
		for (String word : line.split("\t")) {
			columns.add(word);
		}
		closeFile();
	}

	public void addFildLineToDB(DB db) throws IOException, ParseException {
		String line;
		Gson gson = new Gson();
		openFile();
		bufferedReader.readLine(); // skiping 1st line
		while ((line = bufferedReader.readLine()) != null) {
			int i = 0;
			Ad ad = new Ad();
			User user = new User();
			for (String word : line.split("\t")) {
				if (!word.isEmpty()) {
					switch (columns.get(i)) {
					case "id":
						ad.setId(Integer.parseInt(word));
						break;
					case "user_id":
						ad.setUserId(word);
						user.setUserId(word);
						break;
					case "user_created_at":
						user.setUserCreatedAt(new SimpleDateFormat(
								"yyyy-mm-dd hh:mm:ss").parse(word));
						break;
					case "user_phone":
						user.setUserPhone(word);
						break;
					case "user_skype":
						user.setUserSkype(word);
						break;
					case "user_email_domain":
						user.setUserEmailDomain(word);
						break;
					case "created_at":
						ad.setCreatedAt(new SimpleDateFormat(
								"yyyy-mm-dd hh:mm:ss").parse(word));
						break;
					case "title":
						ad.setTitle(word);
						break;
					case "description":
						ad.setDescription(word);
						break;
					case "region":
						ad.setRegion(Integer.parseInt(word));
						break;
					case "city":
						ad.setCity(Integer.parseInt(word));
						break;
					case "private_business":
						ad.setPrivateAd(word.equals("private"));
						break;
					case "offer_seek":
						ad.setOfferAd(word.equals("offer"));
						break;
					case "category":
						ad.setCategory(Integer.parseInt(word));
						break;
					case "category_l1":
						ad.setCategoryLi1(Integer.parseInt(word));
						break;
					case "category_l2":
						ad.setCategoryLi2(Integer.parseInt(word));
						break;
					case "category_l3":
						ad.setCategoryLi3(Integer.parseInt(word));
						break;
					case "photos":
						ad.setNoumberOfPhotos(Integer.parseInt(word));
						break;
					case "country":
						ad.setCountry(word);
						break;
					case "ip":
						ad.setIp(word);
						break;
					case "attributes":
						ad.setAttributes(word);
						break;
					case "human":
						ad.setHuman(word.equals("human"));
						break;
					case "status-details":
						ad.setStatusDetails(word);
						break;
					case "status":
						ad.setStatus(Integer.parseInt(word));
						break;
					}
				}

				//
				i++;
			}
			// if (!find(db, "ads_testing","id",ad.getId())) {
			try {
				addToDb(db, gson.toJson(ad), adsTableName);
				// }
				// if (!find(db, "users_testing", "userId", user.getUserId())) {
				addToDb(db, gson.toJson(user), usersTableName);
			} catch (MongoException e) {

			}
			// }
		}
		closeFile();

	}

	//
	// private Boolean find(DB db, String table, String searchColumn,
	// Object searchFor) {
	// BasicDBObject whereQuery = new BasicDBObject();
	// whereQuery.put(searchColumn, searchFor);
	// DBCursor cursor = db.getCollection(table).find(whereQuery);
	// return cursor.hasNext();
	// }

	private void addToDb(DB db, String json, String table) {
		DBObject dbObject = (DBObject) JSON.parse(json);
		db.getCollection(table).insert(dbObject);
	}
}
