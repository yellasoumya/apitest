package stepDefinitions;

import clients.MusicTrackAPIClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class MusicTrackSteps {
    private static final String URL = "https://testapi.io/api/ottplatform/";
    public static Response response;

    @Given("there are music tracks exists")
    public void thereAreExistingMusicTracks() {
        response = new MusicTrackAPIClient().getMusicTracks(URL + "media");
        int musicTracks = response.jsonPath().getList("data").size();
        log.info("The number of music tracks present are : " + musicTracks);
        assertThat("no music tracks present", musicTracks, is(greaterThan(0)));
    }

    @When("^I make a request to get the existing music tracks$")
    public void iMakeARequestToGetTheExistingMusicTracks() {
        response = new MusicTrackAPIClient().getMusicTracks(URL + "media");
        log.info("Music tracks retrieved successfully : " + response.jsonPath().getList("data"));
    }

    @Then("^the response code should be (\\d+)$")
    public void theResponseCodeIs(int responseCode) {
        assertThat("The response code is not matched!", responseCode, is(equalTo(response.getStatusCode())));
    }

    @And("^the response time should be below (\\d+) milliseconds$")
    public void theResponseTimeIsBelowMilliseconds(long number) {
        assertThat(response.getTime(), is(lessThan(number)));
    }

    @And("the id field should be never null or empty")
    public void theIdFieldIsNeverNullOrEmpty() {
        List<String> ids = response.jsonPath().getList("data.id");
        log.info("Music Track ids retrieved successfully" + ids);
        for (String id : ids) {
            assertThat("id : " + id + "value is null", id, not(isEmptyOrNullString()));
        }
    }

    @And("the segment type for every track should always be music")
    public void theSegmentTypeForEveryTrackShouldAlwaysMusic() {
        List<String> ids = response.jsonPath().getList("data.id");
        List<String> segmentTypes = response.jsonPath().getList("data.segment_type");
        log.info("Music Track segment types fetched successfully" + segmentTypes);
        for (int i = 0; i < ids.size(); i++) {
            assertThat("Segment type is not 'music' for id :" + ids.get(i), segmentTypes.get(i), is(equalToIgnoringCase("music")));
        }
    }

    @And("the primary field should be never null or empty")
    public void thePrimaryFieldShouldBeNeverNullOrEmpty() {
        List<String> ids = response.jsonPath().getList("data.id");
        List<String> primaryFields = response.jsonPath().getList("data.title_list.primary");
        log.info("Music Track primary fields fetched successfully" + primaryFields);
        for (int i = 0; i < ids.size(); i++) {
            assertThat("The value of Primary Field : " + primaryFields.get(i) + " is null for id :" + ids.get(i), primaryFields.get(i), not(isEmptyOrNullString()));
        }
    }

    @And("only one track in the list should be now_playing to be true")
    public void onlyOneTrackInTheListShouldBeNow_playingToBeTrue() {
        List<Object> nowPlaying = response.jsonPath().getList("data.offset.now_playing");
        log.info("now_playing values retrieved successfully " + nowPlaying);
        int counter = 0;
        for (int i = 0; i < nowPlaying.size(); i++) {
            if (nowPlaying.get(i).equals(true)) {
                counter += 1;
            }
        }
        assertThat("The number of tracks playing are :" + counter, counter, is(equalTo(1)));
    }

    @And("the date in the response headers should display as expected")
    public void theDaeInTheResponseHeaders() {
        String dateHeader = response.header("Date");
        log.info("The header for Date has retrieved successfully");
        assertThat("Incorrect date is displayed : " + dateHeader, dateHeader, is(equalTo("Fri, 21 May")));
    }


}
