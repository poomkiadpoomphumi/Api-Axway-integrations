import groovy.json.JsonOutput
import groovy.json.StringEscapeUtils
def invoke(msg) 
{ 
	try{
        def countMax;
        def row = [];
        def array = [];
		def count = 0;
		def obj = msg.get("obj");
        obj.keySet().each { columns ->
            array << columns;
        }
		while (true){
            row[count] = [:];
			array.each { key ->
				row[count][key] = obj[key]["values"][count];
			}
			count++;
			if(obj[array[0]]["values"].size() == count){
				break;
			} 
		} 
		def json_beauty = JsonOutput.toJson(row);
		msg.put("jsonRsp",json_beauty);
        return true;
    } catch(Exception e) {
        msg.put("error",e.toString());
        return false;
    }
}