Feature: Retrieve Info details for Ethereum currency and validate
  As an end user of the coinmarketcap api
  verify that user should be able to retrieve the info of Ethereum currency
  verify the response details

  @Test2 @Tests
  Scenario: Retrieve the info for Ethereum currency and validate
    Given the user make get info call for Ethereum with id 1027
    Then verify the below details in the response
      | logo          | https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png |
      | technical_doc | https://github.com/ethereum/wiki/wiki/White-Paper            |
      | symbol        | ETH                                                          |
      | date_added    | 2015-08-07T00:00:00.000Z                                     |
      | platform      | null                                                         |
      | tags          | mineable                                                     |
