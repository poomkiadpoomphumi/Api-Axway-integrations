import groovy.json.JsonOutput
def invoke(msg) 
{ 
    try{
        def countMax;
        def row = [];
        def array = [];
        def count = 0;
        def tag_gmdr_scada = '';
        def tag_gmdr_client = '';
        def obj = msg.get("obj");
        msg.keySet().each { columns ->
            array << columns;
        }
        while (true){
            row[count] = [:];
            array.each { key ->
                row[count][key] = obj[key]["values"][count];
                (obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '1') ?
                tag_gmdr_scada = obj["gmdr_scada_main_1"]["values"][count] : tag_gmdr_scada;
                (obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '1') ? 
                tag_gmdr_client = obj["gmdr_client_main_1"]["values"][count] : tag_gmdr_client;
                if(tag_gmdr_scada != null && tag_gmdr_client != null && 
                obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '1' && 
                obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '1'){ 
                row[count]["source_name"] = 'GMDR SCADA + GMDR CLIENT'; 
                }else if(tag_gmdr_scada != null && tag_gmdr_client == null && 
                obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '1' && 
                obj["source"]["values"][count] == '4' && obj["run"]["values"][count]== '1'){ 
                row[count]["source_name"] = 'GMDR SCADA'; 
                }else if(tag_gmdr_scada == null && tag_gmdr_client != null && 
                obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '1' && 
                obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '1'){ 
                row[count]["source_name"] = 'GMDR CLIENT';
                }else if(obj["source"]["values"][count] == '5'){row[count]["source_name"] = 'ARCH SCADA';
                }else if(obj["source"]["values"][count] == '6'){row[count]["source_name"] = 'REALTIME SCADA'};

                if(obj["source"]["values"][count] == '1'){
                row[count]["tag"] = obj["monthly_client_id"]["values"][count];
                row[count]["source_name"] = 'MONTHLY BILLING';
                }else if(obj["source"]["values"][count] == '2'){ 
                row[count]["tag"] = obj["daily_client_id"]["values"][count];
                row[count]["source_name"] = 'DAILY BILLING';
                }else if(obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '1'){ 
                row[count]["tag"] = obj["gmdr_scada_main_1"]["values"][count];
                }else if(obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '2'){ 
                row[count]["tag"] = obj["gmdr_scada_main_2"]["values"][count];
                }else if(obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '3'){ 
                row[count]["tag"] = obj["gmdr_scada_main_3"]["values"][count];
                }else if(obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '4'){ 
                row[count]["tag"] = obj["gmdr_scada_main_4"]["values"][count];
                }else if(obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '5'){ 
                row[count]["tag"] = obj["gmdr_scada_main_5"]["values"][count];
                }else if(obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '6'){ 
                row[count]["tag"] = obj["gmdr_scada_main_6"]["values"][count];
                }else if(obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '7'){ 
                row[count]["tag"] = obj["gmdr_scada_back_1"]["values"][count];
                }else if(obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '8'){ 
                row[count]["tag"] = obj["gmdr_scada_back_2"]["values"][count];
                }else if(obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '9'){ 
                row[count]["tag"] = obj["gmdr_scada_back_3"]["values"][count];
                }else if(obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '10'){ 
                row[count]["tag"] = obj["gmdr_scada_back_4"]["values"][count];
                }else if(obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '11'){ 
                row[count]["tag"] = obj["gmdr_scada_back_5"]["values"][count];
                }else if(obj["source"]["values"][count] == '3' && obj["run"]["values"][count] == '12'){ 
                row[count]["tag"] = obj["gmdr_scada_back_6"]["values"][count];
                }else if(obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '1'){ 
                row[count]["tag"] = obj["gmdr_client_main_1"]["values"][count];
                }else if(obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '2'){ 
                row[count]["tag"] = obj["gmdr_client_main_2"]["values"][count];
                }else if(obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '3'){ 
                row[count]["tag"] = obj["gmdr_client_main_3"]["values"][count];
                }else if(obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '4'){ 
                row[count]["tag"] = obj["gmdr_client_main_4"]["values"][count];
                }else if(obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '5'){ 
                row[count]["tag"] = obj["gmdr_client_main_5"]["values"][count];
                }else if(obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '6'){ 
                row[count]["tag"] = obj["gmdr_client_main_6"]["values"][count];
                }else if(obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '7'){ 
                row[count]["tag"] = obj["gmdr_client_back_1"]["values"][count];
                }else if(obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '8'){ 
                row[count]["tag"] = obj["gmdr_client_back_2"]["values"][count];
                }else if(obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '9'){ 
                row[count]["tag"] = obj["gmdr_client_back_3"]["values"][count];
                }else if(obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '10'){ 
                row[count]["tag"] = obj["gmdr_client_back_4"]["values"][count];
                }else if(obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '11'){ 
                row[count]["tag"] = obj["gmdr_client_back_5"]["values"][count];
                }else if(obj["source"]["values"][count] == '4' && obj["run"]["values"][count] == '12'){ 
                row[count]["tag"] = obj["gmdr_client_back_6"]["values"][count];
                }else if(obj["source"]["values"][count] == '5' && obj["run"]["values"][count] == '1'){ 
                row[count]["tag"] = obj["arch_scada_volume_tag_1"]["values"][count];
                }else if(obj["source"]["values"][count] == '5' && obj["run"]["values"][count] == '2'){ 
                row[count]["tag"] = obj["arch_scada_volume_tag_2"]["values"][count];
                }else if(obj["source"]["values"][count] == '5' && obj["run"]["values"][count] == '3'){ 
                row[count]["tag"] = obj["arch_scada_volume_tag_3"]["values"][count];
                }else if(obj["source"]["values"][count] == '5' && obj["run"]["values"][count] == '4'){ 
                row[count]["tag"] = obj["arch_scada_volume_tag_4"]["values"][count];
                }else if(obj["source"]["values"][count] == '5' && obj["run"]["values"][count] == '5'){ 
                row[count]["tag"] = obj["arch_scada_volume_tag_5"]["values"][count];
                }else if(obj["source"]["values"][count] == '5' && obj["run"]["values"][count] == '6'){ 
                row[count]["tag"] = obj["arch_scada_volume_tag_6"]["values"][count];
                }else if(obj["source"]["values"][count] == '5' && obj["run"]["values"][count] == '7'){ 
                row[count]["tag"] = obj["arch_scada_energy_tag_1"]["values"][count];
                }else if(obj["source"]["values"][count] == '5' && obj["run"]["values"][count] == '8'){ 
                row[count]["tag"] = obj["arch_scada_energy_tag_2"]["values"][count];
                }else if(obj["source"]["values"][count] == '5' && obj["run"]["values"][count] == '9'){ 
                row[count]["tag"] = obj["arch_scada_energy_tag_3"]["values"][count];
                }else if(obj["source"]["values"][count] == '5' && obj["run"]["values"][count] == '10'){ 
                row[count]["tag"] = obj["arch_scada_energy_tag_4"]["values"][count];
                }else if(obj["source"]["values"][count] == '5' && obj["run"]["values"][count] == '11'){ 
                row[count]["tag"] = obj["arch_scada_energy_tag_5"]["values"][count];
                }else if(obj["source"]["values"][count] == '5' && obj["run"]["values"][count] == '12'){ 
                row[count]["tag"] = obj["arch_scada_energy_tag_6"]["values"][count];
                }else if(obj["source"]["values"][count] == '6' && obj["run"]["values"][count] == '1'){ 
                row[count]["tag"] = obj["realtime_scada_volume_tag_1"]["values"][count];
                }else if(obj["source"]["values"][count] == '6' && obj["run"]["values"][count] == '2'){ 
                row[count]["tag"] = obj["realtime_scada_volume_tag_2"]["values"][count];
                }else if(obj["source"]["values"][count] == '6' && obj["run"]["values"][count] == '3'){ 
                row[count]["tag"] = obj["realtime_scada_volume_tag_3"]["values"][count];
                }else if(obj["source"]["values"][count] == '6' && obj["run"]["values"][count] == '4'){ 
                row[count]["tag"] = obj["realtime_scada_volume_tag_4"]["values"][count];
                }else if(obj["source"]["values"][count] == '6' && obj["run"]["values"][count] == '5'){ 
                row[count]["tag"] = obj["realtime_scada_volume_tag_5"]["values"][count];
                }else if(obj["source"]["values"][count] == '6' && obj["run"]["values"][count] == '6'){ 
                row[count]["tag"] = obj["realtime_scada_volume_tag_6"]["values"][count];
                }else if(obj["source"]["values"][count] == '6' && obj["run"]["values"][count] == '7'){ 
                row[count]["tag"] = obj["realtime_scada_energy_tag_1"]["values"][count];
                }else if(obj["source"]["values"][count] == '6' && obj["run"]["values"][count] == '8'){ 
                row[count]["tag"] = obj["realtime_scada_energy_tag_2"]["values"][count];
                }else if(obj["source"]["values"][count] == '6' && obj["run"]["values"][count] == '9'){ 
                row[count]["tag"] = obj["realtime_scada_energy_tag_3"]["values"][count];
                }else if(obj["source"]["values"][count] == '6' && obj["run"]["values"][count] == '10'){ 
                row[count]["tag"] = obj["realtime_scada_energy_tag_4"]["values"][count];
                }else if(obj["source"]["values"][count] == '6' && obj["run"]["values"][count] == '11'){ 
                row[count]["tag"] = obj["realtime_scada_energy_tag_5"]["values"][count];
                }else if(obj["source"]["values"][count] == '6' && obj["run"]["values"][count] == '12'){ 
                row[count]["tag"] = obj["realtime_scada_energy_tag_6"]["values"][count];
                }else{row[count]["tag"] = null;}
            }
            count++
            if(obj[array[0]]["values"].size() == count){
                break;
            } 
        }
        def json_beauty = new String(JsonOutput.prettyPrint(JsonOutput.toJson(row)).getBytes("UTF-8"));
		msg.put("jsonRsp",json_beauty);
        return true;
    } catch(Exception e) {
        msg.put("error",e.toString());
        return false;
    }
}


/* def obj = [
               "update_timestamp": [
                   "namespace": "##nonamespace##",
                   "values": [
                       "1692230400001",
                       "1692230400002",
                       "1692230400003"
                   ],
                   "namespaces": [
                       "urn:vordel:attribute:1.0"
                   ],
                   "numberValues": 1478,
                   "key": "update_timestamp",
                   "namespaceForAssertion": "urn:vordel:attribute:1.0",
                   "name": "update_timestamp"
               ],
               "error_type": [
                   "namespace": "##nonamespace##",
                   "values": [
                       "sdgsfgsfgsf",
                       "fhjghkhjkh",
                       "hjkhjkhjkhj"
                   ],
                   "namespaces": [
                       "urn:vordel:attribute:1.0"
                   ],
                   "numberValues": 1478,
                   "key": "update_timestamp",
                   "namespaceForAssertion": "urn:vordel:attribute:1.0",
                   "name": "update_timestamp"
               ],
                "source": [
                   "namespace": "##nonamespace##",
                   "values": [
                       "1",
                       "2",
                       "3"
                   ],
                   "namespaces": [
                       "urn:vordel:attribute:1.0"
                   ],
                   "numberValues": 1478,
                   "key": "update_timestamp",
                   "namespaceForAssertion": "urn:vordel:attribute:1.0",
                   "name": "update_timestamp"
               ],
           ] */
    