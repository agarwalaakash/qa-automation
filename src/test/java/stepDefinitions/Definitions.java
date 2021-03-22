package stepDefinitions;

import Pages.Midtrans;
import Reusable.Reusable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Definitions extends Reusable {

    Midtrans midtrans = new Midtrans();

    @Given("^user launch the store site$")
    public void user_launch_the_store_site() {
        midtrans.user_launch_the_store_site();
    }

    @Given("^user clicks on buy now$")
    public void user_clicks_on_buy_now() {
        midtrans.user_clicks_on_buy_now();
    }

    @When("^click on checkout with credit card details as \"(.*)\" and \"(.*)\" and \"(.*)\" and \"(.*)\"$")
    public void click_on_checkout_with_credit_card(String cardnumber,String expirydate,String cvv,String otp) {
        midtrans.click_on_checkout_with_credit_card(cardnumber,expirydate,cvv,otp);
    }

    @Then("^verify transaction successful message$")
    public void transaction_successful_message() {
        midtrans.transaction_successful_message();
    }

    @Then("^user navigates to home page$")
    public void user_navigates_to_home_page() {
        midtrans.user_navigates_to_home_page();
    }

    @Then("^verify purchase successful message$")
    public void verify_purchase_successful_message() {
        midtrans.verify_purchase_successful_message();
    }

    @Then("^verify transaction failure message$")
    public void transaction_failure_message() {
        midtrans.transaction_failure_message();
    }

}
