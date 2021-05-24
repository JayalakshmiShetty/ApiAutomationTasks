package ApIActions;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class APITasks extends APIMethods {
    public void verifyThatCurrenciesAreConvertedToBolivian(List<String> currencies) {
        Response result=null;
        String jsonAsString;
        Response ResponseObject;
        ResponseObject = null;
        ResponseObject = given().queryParam("CMC_PRO_API_KEY", Constant.API_KEY)
                .contentType(Constant.content)
                .when().get(Constant.BASE_URL+Constant.MAPPATH).then()
                .statusCode(200).extract().response();
        List<Integer> ids=getIDFromResponse(currencies);
        for(int id:ids){
            result=given().queryParam("CMC_PRO_API_KEY", Constant.API_KEY)
                    .queryParam( "convert_id", id).queryParam("amount",11).queryParam("id", 2832)
                    .contentType(Constant.content).when().get(Constant.BASE_URL+Constant.CONVERSIONPATH)
                    .then().and().log().body().statusCode(200).extract().response();
        }    }


    public void verifyEtherumCurrencyInfo() {
        getInfoCallForCurrency(1027);

        verifyInfoCallDetail("logo", "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png");


    }
}
