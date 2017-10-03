package docs;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;;

public class JSON {
	
	public static void main(String args[])
	{
		JSONObject json;
		JSONParser jsonParser;
		String str = "{\"Name\":\"Shadat\"}";
		json = new JSONObject();
		jsonParser = new JSONParser();
		try {
			Object obj = jsonParser.parse(str);
			json = (JSONObject) obj;
			System.out.println(json.toJSONString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void isJson()
	{
		
	}

}
