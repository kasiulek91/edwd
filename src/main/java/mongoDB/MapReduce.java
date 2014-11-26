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
	// "\"\",\"a\",\"aby\",\"ach\",\"acz\",\"aczkolwiek\",\"aj\",\"albo\",\"ale\",\"ale�\",\"ani\",\"a�\",\"bardziej\",\"bardzo\",\"bo\",\"bowiem\",\"by\",\"byli\",\"bynajmniej\",\"by�\",\"by�\",\"by�a\",\"by�o\",\"by�y\",\"b�dzie\",\"b�d�\",\"cali\",\"ca�a\",\"ca�y\",\"ci\",\"ci�\",\"ciebie\",\"co\",\"cokolwiek\",\"co�\",\"czasami\",\"czasem\",\"czemu\",\"czy\",\"czyli\",\"daleko\",\"dla\",\"dlaczego\",\"dlatego\",\"do\",\"dobrze\",\"dok�d\",\"do��\",\"du�o\",\"dwa\",\"dwaj\",\"dwie\",\"dwoje\",\"dzi�\",\"dzisiaj\",\"gdy\",\"gdyby\",\"gdy�\",\"gdzie\",\"gdziekolwiek\",\"gdzie�\",\"i\",\"ich\",\"ile\",\"im\",\"inna\",\"inne\",\"inny\",\"innych\",\"i�\",\"ja\",\"j�\",\"jak\",\"jaka�\",\"jakby\",\"jaki\",\"jakich�\",\"jakie\",\"jaki�\",\"jaki�\",\"jakkolwiek\",\"jako\",\"jako�\",\"je\",\"jeden\",\"jedna\",\"jedno\",\"jednak\",\"jednak�e\",\"jego\",\"jej\",\"jemu\",\"jest\",\"jestem\",\"jeszcze\",\"je�li\",\"je�eli\",\"ju�\",\"j�\",\"ka�dy\",\"kiedy\",\"kilka\",\"kim�\",\"kto\",\"ktokolwiek\",\"kto�\",\"kt�ra\",\"kt�re\",\"kt�rego\",\"kt�rej\",\"kt�ry\",\"kt�rych\",\"kt�rym\",\"kt�rzy\",\"ku\",\"lat\",\"lecz\",\"lub\",\"ma\",\"maj�\",\"ma�o\",\"mam\",\"mi\",\"mimo\",\"mi�dzy\",\"mn�\",\"mnie\",\"mog�\",\"moi\",\"moim\",\"moja\",\"moje\",\"mo�e\",\"mo�liwe\",\"mo�na\",\"m�j\",\"mu\",\"musi\",\"my\",\"na\",\"nad\",\"nam\",\"nami\",\"nas\",\"nasi\",\"nasz\",\"nasza\",\"nasze\",\"naszego\",\"naszych\",\"natomiast\",\"natychmiast\",\"nawet\",\"ni�\",\"nic\",\"nich\",\"nie\",\"niech\",\"niego\",\"niej\",\"niemu\",\"nigdy\",\"nim\",\"nimi\",\"ni�\",\"no\",\"o\",\"obok\",\"od\",\"oko�o\",\"on\",\"ona\",\"one\",\"oni\",\"ono\",\"oraz\",\"oto\",\"owszem\",\"pan\",\"pana\",\"pani\",\"po\",\"pod\",\"podczas\",\"pomimo\",\"ponad\",\"poniewa�\",\"powinien\",\"powinna\",\"powinni\",\"powinno\",\"poza\",\"prawie\",\"przecie�\",\"przed\",\"przede\",\"przedtem\",\"przez\",\"przy\",\"roku\",\"r�wnie�\",\"sam\",\"sama\",\"s�\",\"si�\",\"sk�d\",\"sobie\",\"sob�\",\"spos�b\",\"swoje\",\"ta\",\"tak\",\"taka\",\"taki\",\"takie\",\"tak�e\",\"tam\",\"te\",\"tego\",\"tej\",\"temu\",\"ten\",\"teraz\",\"te�\",\"to\",\"tob�\",\"tobie\",\"tote�\",\"trzeba\",\"tu\",\"tutaj\",\"twoi\",\"twoim\",\"twoja\",\"twoje\",\"twym\",\"tw�j\",\"ty\",\"tych\",\"tylko\",\"tym\",\"u\",\"w\",\"wam\",\"wami\",\"was\",\"wasz\",\"wasza\",\"wasze\",\"we\",\"wed�ug\",\"wiele\",\"wielu\",\"wi�c\",\"wi�cej\",\"wszyscy\",\"wszystkich\",\"wszystkie\",\"wszystkim\",\"wszystko\",\"wtedy\",\"wy\",\"w�a�nie\",\"z\",\"za\",\"zapewne\",\"zawsze\",\"ze\",\"z�\",\"znowu\",\"zn�w\",\"zosta�\",\"�aden\",\"�adna\",\"�adne\",\"�adnych\",\"�e\",\"�eby\"";
	private final String[] stopwords = { "a", "aby", "ach", "acz",
			"aczkolwiek", "aj", "albo", "ale", "ale�", "ani", "a�", "bardziej",
			"bardzo", "bo", "bowiem", "by", "byli", "bynajmniej", "by�", "by�",
			"by�a", "by�o", "by�y", "b�dzie", "b�d�", "cali", "ca�a", "ca�y",
			"ci", "ci�", "ciebie", "co", "cokolwiek", "co�", "czasami",
			"czasem", "czemu", "czy", "czyli", "daleko", "dla", "dlaczego",
			"dlatego", "do", "dobrze", "dok�d", "do��", "du�o", "dwa", "dwaj",
			"dwie", "dwoje", "dzi�", "dzisiaj", "gdy", "gdyby", "gdy�",
			"gdzie", "gdziekolwiek", "gdzie�", "i", "ich", "ile", "im", "inna",
			"inne", "inny", "innych", "i�", "ja", "j�", "jak", "jaka�",
			"jakby", "jaki", "jakich�", "jakie", "jaki�", "jaki�",
			"jakkolwiek", "jako", "jako�", "je", "jeden", "jedna", "jedno",
			"jednak", "jednak�e", "jego", "jej", "jemu", "jest", "jestem",
			"jeszcze", "je�li", "je�eli", "ju�", "j�", "ka�dy", "kiedy",
			"kilka", "kim�", "kto", "ktokolwiek", "kto�", "kt�ra", "kt�re",
			"kt�rego", "kt�rej", "kt�ry", "kt�rych", "kt�rym", "kt�rzy", "ku",
			"lat", "lecz", "lub", "ma", "maj�", "ma�o", "mam", "mi", "mimo",
			"mi�dzy", "mn�", "mnie", "mog�", "moi", "moim", "moja", "moje",
			"mo�e", "mo�liwe", "mo�na", "m�j", "mu", "musi", "my", "na", "nad",
			"nam", "nami", "nas", "nasi", "nasz", "nasza", "nasze", "naszego",
			"naszych", "natomiast", "natychmiast", "nawet", "ni�", "nic",
			"nich", "nie", "niech", "niego", "niej", "niemu", "nigdy", "nim",
			"nimi", "ni�", "no", "o", "obok", "od", "oko�o", "on", "ona",
			"one", "oni", "ono", "oraz", "oto", "owszem", "pan", "pana",
			"pani", "po", "pod", "podczas", "pomimo", "ponad", "poniewa�",
			"powinien", "powinna", "powinni", "powinno", "poza", "prawie",
			"przecie�", "przed", "przede", "przedtem", "przez", "przy", "roku",
			"r�wnie�", "sam", "sama", "s�", "si�", "sk�d", "sobie", "sob�",
			"spos�b", "swoje", "ta", "tak", "taka", "taki", "takie", "tak�e",
			"tam", "te", "tego", "tej", "temu", "ten", "teraz", "te�", "to",
			"tob�", "tobie", "tote�", "trzeba", "tu", "tutaj", "twoi", "twoim",
			"twoja", "twoje", "twym", "tw�j", "ty", "tych", "tylko", "tym",
			"u", "w", "wam", "wami", "was", "wasz", "wasza", "wasze", "we",
			"wed�ug", "wiele", "wielu", "wi�c", "wi�cej", "wszyscy",
			"wszystkich", "wszystkie", "wszystkim", "wszystko", "wtedy", "wy",
			"w�a�nie", "z", "za", "zapewne", "zawsze", "ze", "z�", "znowu",
			"zn�w", "zosta�", "�aden", "�adna", "�adne", "�adnych", "�e",
			"�eby" };

	public MapReduce(String field) {
		map = "function() { "
				+ "var text=this."
				+ field
				+ "; "
				// + "var stopwords=["
				// + stopwordsString
				// + "]; "
				+ "text=text.replace(/[^a-zA-Z�����깜��ƥ�ʣ�� ]/g,'').toLowerCase().split(' ');"
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
