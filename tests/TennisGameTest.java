import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {

    // Here is the format of the scores: "player1Score - player2Score"
    // "love - love"
    // "15 - 15"
    // "30 - 30"
    // "deuce"
    // "15 - love", "love - 15"
    // "30 - love", "love - 30"
    // "40 - love", "love - 40"
    // "30 - 15", "15 - 30"
    // "40 - 15", "15 - 40"
    // "player1 has advantage"
    // "player2 has advantage"
    // "player1 wins"
    // "player2 wins"

	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}		
	
    @Test
    public void testTennisGame_Player1ScoresFirst() throws TennisGameException {
        // Arrange
        TennisGame game = new TennisGame();

        // Act
        game.player1Scored();
        String score = game.getScore();

        // Assert
        assertEquals("Score after Player1 scores first incorrect", "15 - love", score);
    }

    @Test
    public void testTennisGame_Player2ScoresFirst() throws TennisGameException {
        // Arrange
        TennisGame game = new TennisGame();

        // Act
        game.player2Scored();
        String score = game.getScore();

        // Assert
        assertEquals("Score after Player2 scores first incorrect", "love - 15", score);
    }

    @Test
    public void testTennisGame_Player1Advantage() throws TennisGameException {
        // Arrange
        TennisGame game = new TennisGame();

        // Act
        game.player1Scored();
        game.player1Scored();
        game.player1Scored();
        game.player2Scored();
        game.player2Scored();
        game.player2Scored();
        game.player1Scored(); // Player1 gains advantage

        // Act
        String score = game.getScore();

        // Assert
        assertEquals("Player1 advantage", "player1 has advantage", score);
    }

    @Test
    public void testTennisGame_Player2Advantage() throws TennisGameException {
        // Arrange
        TennisGame game = new TennisGame();

        // Act
        game.player1Scored();
        game.player1Scored();
        game.player1Scored();
        game.player2Scored();
        game.player2Scored();
        game.player2Scored();
        game.player2Scored(); // Player2 gains advantage

        // Act
        String score = game.getScore();

        // Assert
        assertEquals("Player2 advantage", "player2 has advantage", score);
    }

    @Test
    public void testTennisGame_Player1Wins() throws TennisGameException {
        // Arrange
        TennisGame game = new TennisGame();

        // Act
        game.player1Scored();
        game.player1Scored();
        game.player1Scored();
        game.player2Scored();
        game.player2Scored();
        game.player1Scored(); // Player1 wins

        // Act
        String score = game.getScore();

        // Assert
        assertEquals("Player1 win", "player1 wins", score);
    }

    @Test
    public void testTennisGame_Player2Wins() throws TennisGameException {
        // Arrange
        TennisGame game = new TennisGame();

        // Act
        game.player1Scored();
        game.player1Scored();
        game.player2Scored();
        game.player2Scored();
        game.player2Scored();
        game.player2Scored(); // Player2 wins

        // Act
        String score = game.getScore();

        // Assert
        assertEquals("Player2 win", "player2 wins", score);
    }

    @Test
    public void testTennisGame_AdvantageToWin() throws TennisGameException {
        // Arrange
        TennisGame game = new TennisGame();

        // Act
        game.player1Scored();
        game.player1Scored();
        game.player1Scored();
        game.player2Scored();
        game.player2Scored();
        game.player2Scored();
        game.player1Scored(); // Player1 gets advantage
        game.player1Scored(); // Player1 wins

        // Act
        String score = game.getScore();

        // Assert
        assertEquals("Player1 win", "player1 wins", score);
    }

    @Test(expected = TennisGameException.class)
    public void testTennisGame_Player2ScoresAfterGameEnds() throws TennisGameException {
        // Arrange
        TennisGame game = new TennisGame();

        //Act
        game.player2Scored();
        game.player2Scored();
        game.player2Scored();
        game.player2Scored();

        //Act
        game.player2Scored(); 
    }
}
