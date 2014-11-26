package mongoDB;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceOutput;
import com.mongodb.util.JSON;

public class MapReduce {
	private String map;
	private String reduce;
	private MapReduceCommand mapReduceCommand;
	private MapReduceOutput out;
	// private final String stopwordsString =
	// "\"\",\"a\",\"aby\",\"ach\",\"acz\",\"aczkolwiek\",\"aj\",\"albo\",\"ale\",\"ale¿\",\"ani\",\"a¿\",\"bardziej\",\"bardzo\",\"bo\",\"bowiem\",\"by\",\"byli\",\"bynajmniej\",\"byæ\",\"by³\",\"by³a\",\"by³o\",\"by³y\",\"bêdzie\",\"bêd¹\",\"cali\",\"ca³a\",\"ca³y\",\"ci\",\"ciê\",\"ciebie\",\"co\",\"cokolwiek\",\"coœ\",\"czasami\",\"czasem\",\"czemu\",\"czy\",\"czyli\",\"daleko\",\"dla\",\"dlaczego\",\"dlatego\",\"do\",\"dobrze\",\"dok¹d\",\"doœæ\",\"du¿o\",\"dwa\",\"dwaj\",\"dwie\",\"dwoje\",\"dziœ\",\"dzisiaj\",\"gdy\",\"gdyby\",\"gdy¿\",\"gdzie\",\"gdziekolwiek\",\"gdzieœ\",\"i\",\"ich\",\"ile\",\"im\",\"inna\",\"inne\",\"inny\",\"innych\",\"i¿\",\"ja\",\"j¹\",\"jak\",\"jakaœ\",\"jakby\",\"jaki\",\"jakichœ\",\"jakie\",\"jakiœ\",\"jaki¿\",\"jakkolwiek\",\"jako\",\"jakoœ\",\"je\",\"jeden\",\"jedna\",\"jedno\",\"jednak\",\"jednak¿e\",\"jego\",\"jej\",\"jemu\",\"jest\",\"jestem\",\"jeszcze\",\"jeœli\",\"je¿eli\",\"ju¿\",\"j¹\",\"ka¿dy\",\"kiedy\",\"kilka\",\"kimœ\",\"kto\",\"ktokolwiek\",\"ktoœ\",\"która\",\"które\",\"którego\",\"której\",\"który\",\"których\",\"którym\",\"którzy\",\"ku\",\"lat\",\"lecz\",\"lub\",\"ma\",\"maj¹\",\"ma³o\",\"mam\",\"mi\",\"mimo\",\"miêdzy\",\"mn¹\",\"mnie\",\"mog¹\",\"moi\",\"moim\",\"moja\",\"moje\",\"mo¿e\",\"mo¿liwe\",\"mo¿na\",\"mój\",\"mu\",\"musi\",\"my\",\"na\",\"nad\",\"nam\",\"nami\",\"nas\",\"nasi\",\"nasz\",\"nasza\",\"nasze\",\"naszego\",\"naszych\",\"natomiast\",\"natychmiast\",\"nawet\",\"ni¹\",\"nic\",\"nich\",\"nie\",\"niech\",\"niego\",\"niej\",\"niemu\",\"nigdy\",\"nim\",\"nimi\",\"ni¿\",\"no\",\"o\",\"obok\",\"od\",\"oko³o\",\"on\",\"ona\",\"one\",\"oni\",\"ono\",\"oraz\",\"oto\",\"owszem\",\"pan\",\"pana\",\"pani\",\"po\",\"pod\",\"podczas\",\"pomimo\",\"ponad\",\"poniewa¿\",\"powinien\",\"powinna\",\"powinni\",\"powinno\",\"poza\",\"prawie\",\"przecie¿\",\"przed\",\"przede\",\"przedtem\",\"przez\",\"przy\",\"roku\",\"równie¿\",\"sam\",\"sama\",\"s¹\",\"siê\",\"sk¹d\",\"sobie\",\"sob¹\",\"sposób\",\"swoje\",\"ta\",\"tak\",\"taka\",\"taki\",\"takie\",\"tak¿e\",\"tam\",\"te\",\"tego\",\"tej\",\"temu\",\"ten\",\"teraz\",\"te¿\",\"to\",\"tob¹\",\"tobie\",\"tote¿\",\"trzeba\",\"tu\",\"tutaj\",\"twoi\",\"twoim\",\"twoja\",\"twoje\",\"twym\",\"twój\",\"ty\",\"tych\",\"tylko\",\"tym\",\"u\",\"w\",\"wam\",\"wami\",\"was\",\"wasz\",\"wasza\",\"wasze\",\"we\",\"wed³ug\",\"wiele\",\"wielu\",\"wiêc\",\"wiêcej\",\"wszyscy\",\"wszystkich\",\"wszystkie\",\"wszystkim\",\"wszystko\",\"wtedy\",\"wy\",\"w³aœnie\",\"z\",\"za\",\"zapewne\",\"zawsze\",\"ze\",\"z³\",\"znowu\",\"znów\",\"zosta³\",\"¿aden\",\"¿adna\",\"¿adne\",\"¿adnych\",\"¿e\",\"¿eby\"";
	private final String[] stopwords = { "a", "aby", "ach", "acz",
			"aczkolwiek", "aj", "albo", "ale", "ale¿", "ani", "a¿", "bardziej",
			"bardzo", "bo", "bowiem", "by", "byli", "bynajmniej", "byæ", "by³",
			"by³a", "by³o", "by³y", "bêdzie", "bêd¹", "cali", "ca³a", "ca³y",
			"ci", "ciê", "ciebie", "co", "cokolwiek", "coœ", "czasami",
			"czasem", "czemu", "czy", "czyli", "daleko", "dla", "dlaczego",
			"dlatego", "do", "dobrze", "dok¹d", "doœæ", "du¿o", "dwa", "dwaj",
			"dwie", "dwoje", "dziœ", "dzisiaj", "gdy", "gdyby", "gdy¿",
			"gdzie", "gdziekolwiek", "gdzieœ", "i", "ich", "ile", "im", "inna",
			"inne", "inny", "innych", "i¿", "ja", "j¹", "jak", "jakaœ",
			"jakby", "jaki", "jakichœ", "jakie", "jakiœ", "jaki¿",
			"jakkolwiek", "jako", "jakoœ", "je", "jeden", "jedna", "jedno",
			"jednak", "jednak¿e", "jego", "jej", "jemu", "jest", "jestem",
			"jeszcze", "jeœli", "je¿eli", "ju¿", "j¹", "ka¿dy", "kiedy",
			"kilka", "kimœ", "kto", "ktokolwiek", "ktoœ", "która", "które",
			"którego", "której", "który", "których", "którym", "którzy", "ku",
			"lat", "lecz", "lub", "ma", "maj¹", "ma³o", "mam", "mi", "mimo",
			"miêdzy", "mn¹", "mnie", "mog¹", "moi", "moim", "moja", "moje",
			"mo¿e", "mo¿liwe", "mo¿na", "mój", "mu", "musi", "my", "na", "nad",
			"nam", "nami", "nas", "nasi", "nasz", "nasza", "nasze", "naszego",
			"naszych", "natomiast", "natychmiast", "nawet", "ni¹", "nic",
			"nich", "nie", "niech", "niego", "niej", "niemu", "nigdy", "nim",
			"nimi", "ni¿", "no", "o", "obok", "od", "oko³o", "on", "ona",
			"one", "oni", "ono", "oraz", "oto", "owszem", "pan", "pana",
			"pani", "po", "pod", "podczas", "pomimo", "ponad", "poniewa¿",
			"powinien", "powinna", "powinni", "powinno", "poza", "prawie",
			"przecie¿", "przed", "przede", "przedtem", "przez", "przy", "roku",
			"równie¿", "sam", "sama", "s¹", "siê", "sk¹d", "sobie", "sob¹",
			"sposób", "swoje", "ta", "tak", "taka", "taki", "takie", "tak¿e",
			"tam", "te", "tego", "tej", "temu", "ten", "teraz", "te¿", "to",
			"tob¹", "tobie", "tote¿", "trzeba", "tu", "tutaj", "twoi", "twoim",
			"twoja", "twoje", "twym", "twój", "ty", "tych", "tylko", "tym",
			"u", "w", "wam", "wami", "was", "wasz", "wasza", "wasze", "we",
			"wed³ug", "wiele", "wielu", "wiêc", "wiêcej", "wszyscy",
			"wszystkich", "wszystkie", "wszystkim", "wszystko", "wtedy", "wy",
			"w³aœnie", "z", "za", "zapewne", "zawsze", "ze", "z³", "znowu",
			"znów", "zosta³", "¿aden", "¿adna", "¿adne", "¿adnych", "¿e",
			"¿eby" };

	public MapReduce(String field) {
		map = "function() { "
				+ "var text=this."
				+ field
				+ "; "
				// + "var stopwords=["
				// + stopwordsString
				// + "]; "
				+ "text=text.replace(/[^a-zA-Z¿Ÿæñó³ê¹œ¯Æ¥ŒÊ£ÓÑ ]/g,'').toLowerCase().split(' ');"
				+ "for( var i = text.length - 1; i >= 0; i--) {"
				// + " var add = 1;"
				// + "for	(var j = 0; j < stopwords.length; j++)    "
				// + "{if(text[i]==stopwords[j]) {add=0; break;}}"
				+ "if(text[i]) emit(text[i],1); }}";

		reduce = "function( key, values ) {   " + " var count = 0; "
				+ "values.forEach(function(v) {    " + "   count +=v;  "
				+ " });" + "  return count;" + "}";
	}

	public void doMapReduce(DBCollection collection) {

		mapReduceCommand = new MapReduceCommand(collection, map, reduce,
				"output", MapReduceCommand.OutputType.REPLACE, null);
		out = collection.getCollection("ads_testing").mapReduce(
				mapReduceCommand);

	}

	public Boolean notIn(String word) {
		for (String s : stopwords) {
			if (s.equals(word)) {
				return false;
			}
		}
		return true;
	}

	public void printResult() {
		DBCollection dbCollection = out.getOutputCollection();
		DBCursor cursor = dbCollection.find().sort(
				(DBObject) JSON.parse("{value : -1}"));
		int licznik = 0;
		while (cursor.hasNext() && licznik < 100) {
			DBObject dbObject = cursor.next();
			if (notIn((String) dbObject.get("_id"))) {
				System.out.println((licznik + 1) + " . " + dbObject);
				licznik++;
			}
		}
	}

	public MapReduceOutput getOut() {
		return out;
	}
}
