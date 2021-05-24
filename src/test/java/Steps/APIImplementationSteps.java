package Steps;

import ApIActions.APITasks;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;

import java.util.List;

public class APIImplementationSteps extends APITasks {
    List<Integer> currencyIds;


    @Given("the user retrieve the IDs of the below currencies")
    public void the_user_retrieve_the_IDs_of_below_currencies(List<String> currencies) {
        currencyIds=getIDFromResponse(currencies);
    }



    @Given("the user make get info call for Ethereum with id (.*)")
    public void the_user_make_an_info_call_for_Ethereum(long id) {
        getInfoCallForCurrency(id);
    }

    @Then("verify the below details in the response")
    public void the_user_should_able_to_verify_the_below_details(DataTable dataTable) {

        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            verifyInfoCallDetail(columns.get(0), columns.get(1));
        }
    }

    @Given("the user retrieve the info of Id (.*)")
    public void the_user_retrieve_the_info_of_Id(long id) {
        getInfoCallForCurrency(id);
    }

    @Then("verify the currency is having (.*) value as (.*)")
    public void verify_the_currency_with_Id_is_having_mineable_tag(String key, String value) {
        verifyInfoCallDetail(key, value);
    }

    @Then("verify the currency with Id (.*) is having correct cryptocurrency (.*)")
    public void verify_the_currency_with_Id_is_having_correct_cryptocurrency(long id, String expectedCurrencyName) {
       verifyCryptoCurrencyValueInInfoCall(id, expectedCurrencyName);
    }



    @Then("verify that the user converts those currencies to Bolivian Boliviano currency {string}")
    public void verifyThatTheUserConvertsThoseCurrenciesToBolivianBolivianoCurrency(String convertCurrency) {
        verifyThatCurrenciesAreConvertedToBolivian(convertCurrency, currencyIds);
    }
}
