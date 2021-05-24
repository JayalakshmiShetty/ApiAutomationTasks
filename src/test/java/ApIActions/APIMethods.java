package ApIActions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.core.IsNull;

import java.util.ArrayList;
import java.util.List;

import static ApIActions.Constant.BASE_URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class APIMethods {
    Response response=null;
    Response ResponseObject;

    public void getMapResponse(){
        Response result=null;
        String jsonAsString;
        //ResponseObject = null;
        ResponseObject = given().queryParam("CMC_PRO_API_KEY", Constant.API_KEY)
                .contentType(Constant.content)
                .when().get(Constant.BASE_URL+Constant.MAPPATH).then()
                .statusCode(200).extract().response();

    }


    public List<Integer> getIDFromResponse(List<String> currencies) {
        getMapResponse();
        String jsonAsString;
        List<Integer> list = new ArrayList<>();
        jsonAsString = ResponseObject.asString();
        JsonObject jsonObject = new JsonParser().parse(jsonAsString).getAsJsonObject();
        JsonArray arrayObject = jsonObject.getAsJsonArray("data");
        for (int i = 0; i < arrayObject.size(); i++) {
            String taskStatus = arrayObject.get(i).getAsJsonObject().get("symbol").getAsString();
            int idValue = arrayObject.get(i).getAsJsonObject().get("id").getAsInt();
            for(String currency:currencies){
            if (taskStatus.equalsIgnoreCase(currency)) {
                list.add(idValue);}
            }
        }
        System.out.println(list);
        return list;
    }

    public void verifyThatCurrenciesAreConvertedToBolivian(String bolivianID, List<Integer> currencyIDs){
        Response result=null;
        for(int currencyID:currencyIDs){
            result=given().queryParam("CMC_PRO_API_KEY", Constant.API_KEY)
                    .queryParam( "convert_id", currencyID).queryParam("amount",11).queryParam("id", 2832)
                    .contentType(Constant.content).when().get(Constant.BASE_URL+Constant.CONVERSIONPATH)
                    .then().statusCode(200).extract().response();
        }    }


    public void getInfoCallForCurrency(long id){
        //Response response;
        String url = BASE_URL + Constant.INFOPATH;
        response = given().header("X-CMC_PRO_API_KEY", Constant.API_KEY)
                .queryParam("id", id)
                .header("Accept", Constant.content)
                .when()
                .get(url);
        response.then()
                .statusCode(200);
    }

    public void verifyInfoCallDetail(String key, String expectedValue){
        String actualValue = null;
        JsonPath jsonPath = new JsonPath(response.body().asString());
        switch(key){
            case "logo":{
                actualValue = jsonPath.get(Constant.jsonPathCreatorLogoPath);
                break;
            }
            case "technical_doc":{
                actualValue = jsonPath.get(Constant.jsonPathCreatorTechnicalDocPath);
                break;
            }
            case "symbol":{
                actualValue = jsonPath.get(Constant.jsonPathCreatorSymbolPath);
                break;
            }
            case "date_added":{
                actualValue = jsonPath.get(Constant.jsonPathCreatorDateAddedPath);
                break;
            }
            case "platform":{
                actualValue = jsonPath.get(Constant.jsonPathCreatorPlatformPath);
                assertThat(actualValue, is(IsNull.nullValue()));
                break;
            }
            case "tags":{
                actualValue = jsonPath.get(Constant.jsonPathCreatorTagsPath);
                break;
            }
            default:{
                response.then().statusCode(200);
            }
        }
        if(actualValue != null)
            assertThat(actualValue, equalTo(expectedValue));
    }


    public void verifyCryptoCurrencyValueInInfoCall(long id, String expectedCurrencyName){
        String actualCurrencyName = fetchCurrencyNameFromInfoCall(id);
        assertThat(actualCurrencyName, equalTo(expectedCurrencyName));
    }

    private String fetchCurrencyNameFromInfoCall(long id){
        String jsonPathCreatorNamePath = "data."+id+".name";
        JsonPath jsonPath = new JsonPath(response.body().asString());
        return jsonPath.get(jsonPathCreatorNamePath);
    }
}