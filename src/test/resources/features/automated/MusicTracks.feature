@getMusicTracks
Feature: Get Music Tracks
  As a user of the Radio and Music Services
  I want to use an API function to get the music tracks
  So that I can view music track details

  Background: Get the music tracks
    Given there are music tracks exists
    When I make a request to get the existing music tracks
    Then the response code should be 200

  @scenario1 @regression
  Scenario: Verify that the response time of the request is below 1000 milliseconds
    And the response time should be below 1000 milliseconds

  @scenario2 @regression
  Scenario: Verify the id field is never null or empty for any track and segment type for every track is always music
    And the id field should be never null or empty
    And the segment type for every track should always be music

  @scenario3 @regression
  Scenario: Verify the primary field is never null or empty for any track
    And the primary field should be never null or empty

  @scenario4 @regression
  Scenario: Verify only one track in the list has 'now_playing' to be true
    And only one track in the list should be now_playing to be true

  @scenario5 @regression
  Scenario: Verify the Date value in the response headers
    And the date in the response headers should display as expected