package restAssuredWithWireMock.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.MatcherAssert;
import restAssuredWithWireMock.model.Book;
import restAssuredWithWireMock.model.ListOfBooks;
import restAssuredWithWireMock.proxy.BookProxy;
import restAssuredWithWireMock.proxy.UserProxy;

import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation {

    private ListOfBooks listOfBooks;
    private Book book;
    private Response response;

    @Given("user is authorized")
    public void userIsAuthorized(){
        response= UserProxy.getAuthorizedUser();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.getStatusLine()).isEqualTo("HTTP/1.1 200 OK");
    }
    @When("user asks for exactly one book")
    public void getOneBook(){
        book= BookProxy.getOneBook();
    }

    @When("user asks for a list of books")
    public void getAllBooks(){
        listOfBooks= BookProxy.getAllBooks();
    }

    @When("user asks for exactly one book with query param")
    public void userAsksForExacltyOneBookWithQueryParam(){
        book= BookProxy.getOneBookWithQueryParam();
    }
    @When("user adds new book")
    public void userAddsNewBook(){
        response= BookProxy.addBook();
    }
    @When("user updates the book")
    public void userUpdatesTheBook(){
        response= BookProxy.updateBook();
    }
    @When("user deletes a book")
    public void userDeletesABook(){
        response= BookProxy.deleteBook();
    }
    @Then("the list of all books will be shown")
    public void theListOfAllBooksWillBeShown() {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(listOfBooks).isNotNull();
            softly.assertThat(listOfBooks.getBooks().get(0).getId()).isEqualTo("1");
            softly.assertThat(listOfBooks.getBooks().get(0).getBookTitle()).isEqualTo("Java For Testers");
            softly.assertThat(listOfBooks.getBooks().get(0).getAuthor()).isEqualTo("Alan Richardson");
            softly.assertThat(listOfBooks.getBooks().get(1).getId()).isEqualTo("2");
            softly.assertThat(listOfBooks.getBooks().get(1).getBookTitle()).isEqualTo("Automating & Testing a REST API");
            softly.assertThat(listOfBooks.getBooks().get(1).getAuthor()).isEqualTo("Alan Richardson");
            softly.assertThat(listOfBooks.getBooks().get(2).getId()).isEqualTo("3");
            softly.assertThat(listOfBooks.getBooks().get(2).getBookTitle()).isEqualTo("Clean Code");
            softly.assertThat(listOfBooks.getBooks().get(2).getAuthor()).isEqualTo("Robert Cecil Martin");


        });
    }
        @Then("the book will be shown")
        public void theBookWillBeShown(){
            assertThat(book).isNotNull();
            assertThat(book.getId()).isEqualTo("2");
            assertThat(book.getBookTitle()).isEqualTo("Automating & Testing a REST API");
            assertThat(book.getAuthor()).isEqualTo("Alan Richardson");


    }

    @Then("response 201 created will be shown")
    public void responseCreatedWillBeShown(){

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
        Assertions.assertThat(response.getStatusLine()).isEqualTo("HTTP/1.1 201 CREATED");

    }
    @Then("response 200 OK will be shown")
    public void responseOKWillBeShown(){

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Assertions.assertThat(response.getStatusLine()).isEqualTo("HTTP/1.1 200 OK");

    }
    @And("the list of books is available")
    public void theListOfBooksIsAvailable(){listOfBooks=BookProxy.getAllBooks();}

}
