package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jboss.weld.util.collections.Iterables;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.svi.data.Game;
import com.svi.data.SaveGame;
import com.svi.database.RecordDao;
import com.svi.database.RecordDaoImpl;
import com.svi.objects.Record;

class AppTest {
	
	private static String gameId = "newGameId";
	private static String playerId = "Player001";
	private static String symbol = "X";
	private static int location = 0;
	private static String timedate = "04/12/2022";
	
	Game game = new Game(gameId, playerId, symbol, location, timedate);
	
	@BeforeAll
	static void createTable() {
		RecordDao record = new RecordDaoImpl();
		record.createTable();
		System.out.println("Successfully created table.");
	}
	
	@Test
	void test() {
		Calculator test = new Calculator();
		assertEquals(5, test.add(3, 2));
		System.out.println("Testing...");
	}
	
	@Test
	void insertRecord() {
		//Save to database
	    Record record = new Record();
	    game.setGameId(gameId);
	    record.setGameid(game.getGameId());
	    game.setPlayerId(playerId);
	    record.setPlayerid(game.getPlayerId());
	    game.setLocation(location);
		record.setLocation(Integer.toString(game.getLocation()));
		game.setSymbol(symbol);
		record.setSymbol(game.getSymbol());
		game.setCreatedDate(timedate);
		record.setRecorddate(game.getCreatedDate());		
		
		RecordDao recordDao = new RecordDaoImpl();
		recordDao.insertRecords(record);
	}
	
	@Test
	void getRecord() {
		try {
			List<Game> gameIds = new ArrayList<Game>();
			gameIds.add(new Game(game.getGameId(),game.getPlayerId(),game.getSymbol(),game.getLocation(),game.getCreatedDate()));
			System.out.println("Retrieving data...");
			assertEquals(gameIds.toString(), SaveGame.getGames(gameId).toString());
			assertEquals(1, SaveGame.getGames(gameId).size());
			System.out.println("Successfully retrieved data.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterAll
	static void dropTable() {
		RecordDao record = new RecordDaoImpl();
		record.dropTable();
		System.out.println("Successfully dropped table.");
	}
}