Feature: Retrieve ID of Bitcoin, UsdTether and Ethereum and covert them to Bolivian Boliviano
  As an end user of the coinmarketcap api
  verify that Ishould be able to retrieve the ID of few currencies
  verify that currencies are converted to Bolivian Boliviano

  @Test1 @Tests
  Scenario: Retrieve few IDs and covert them to Bolivian Boliviano
    Given the user retrieve the IDs of the below currencies
      | BTC  |
      | USDT |
      | ETH  |
    Then verify that the user converts those currencies to Bolivian Boliviano currency "BOB"
