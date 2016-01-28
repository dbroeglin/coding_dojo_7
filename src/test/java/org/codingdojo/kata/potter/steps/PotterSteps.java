package org.codingdojo.kata.potter.steps;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.codingdojo.kata.potter.PotterDiscounter;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class PotterSteps {
	// Is this safe ? Think about how state is handled during scenario execution...
	private int nbBook1 = 0;
	private int nbBook2 = 0;
	private PotterDiscounter potterDiscounter = new PotterDiscounter();

	@When("I buy $nb copies of book 1")
	@Alias("I buy $nb copy of book 1")
	public void i_buy_book_1(@Named("nb") int nb) {
		nbBook1 = nb;
	}

	@When("I buy $nb cop{y|ies} of book 2")
	public void i_buy_book_2(@Named("nb") int nb) {
		nbBook2 = nb;
	}

	@Then("I should pay $amount")
	public void i_should_pay(@Named("amount") float amount) {
		assertThat(potterDiscounter.calculatePrice(nbBook1, nbBook2),
				equalTo(amount));
	}
}
