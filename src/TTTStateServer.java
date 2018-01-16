import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TTTStateServer extends gameStateServer{
	public String playerX;
	public String playerO;
	public int threadID1;				//threadID of playerX
	public int threadID2;				//threadID of playerO
	public int board[][];
	public TTTStateServer(String playerX, String playerO, int threadID1, int threadID2){
		this.playerX = playerX;
		this.playerO = playerO;
		this.threadID1 = threadID1;
		this.threadID2 = threadID2;
		board = new int[3][3];
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++)
				board[i][j] = 0;
		}
	}
	
	public void winner(String winner){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++)
				board[i][j] = 0;
		}
		if(winner.equals("X")){
			try{
            	String databaseName = "mydb";
    			String databaseUsername = "root";
    			String databasePassword = "root";
    			
    			
        		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
        				databaseUsername,databasePassword);
        		String query = "SELECT TTTWins as wins from userpass  WHERE username = '" + playerX + "'";
        		PreparedStatement myStmt = conn.prepareStatement(query);
        		ResultSet myRs = myStmt.executeQuery();
        		int wins = myRs.getInt("wins");
        		wins++;
        		String insertQuery = "UPDATE userpass SET TTTWins = " + wins + " WHERE username = '" + playerX + "'";
				java.sql.Statement insertQuerystmt = conn.createStatement();
				insertQuerystmt.executeUpdate(insertQuery);	
            	}
            	catch(Exception e){
            	
            	}
		}
		else if(winner.equals("O")){
			try{
			String databaseName = "mydb";
			String databaseUsername = "root";
			String databasePassword = "root";
			
			
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
    				databaseUsername,databasePassword);
    		String query = "SELECT TTTWins as wins from userpass  WHERE username = '" + playerO + "'";
    		PreparedStatement myStmt = conn.prepareStatement(query);
    		ResultSet myRs = myStmt.executeQuery();
    		int wins = myRs.getInt("wins");
    		wins++;
    		String insertQuery = "UPDATE userpass SET TTTWins = " + wins + " WHERE username = '" + playerO + "'";
			java.sql.Statement insertQuerystmt = conn.createStatement();
			insertQuerystmt.executeUpdate(insertQuery);	
        	}
        	catch(Exception e){
        	
        	}
		}
		else if(winner.equals("draw")){
			try{
            	String databaseName = "mydb";
    			String databaseUsername = "root";
    			String databasePassword = "root";
    			
    			
//        		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
//        				databaseUsername,databasePassword);
//        		String insertQuery = "UPDATE userpass SET isLoggedIn = FALSE WHERE username = '" + clientUsername + "'";
//				java.sql.Statement insertQuerystmt = conn.createStatement();
//				insertQuerystmt.executeUpdate(insertQuery);	
            	}
            	catch(Exception e){
            	
            	}
		}
			
		
			
	}
	
	public void updateState(String player, int y, int x){
		String ypos = Integer.toString(y);
		String xpos = Integer.toString(x);
		System.out.println(xpos + " " + ypos);
		if(player.equals(playerX)){
			board[y][x] = 1;			
			try {
				Server.threads[threadID2].dataout.writeBytes("gamemove TTT " + ypos + " " + xpos + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(player.equals(playerO)){
			board[y][x] = -1;
			try {
				Server.threads[threadID1].dataout.writeBytes("gamemove TTT " + ypos + " " + xpos + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateState(String player, int oldx, int oldy, int newx, int newy, int piece) {	//not used in ttt
		// TODO Auto-generated method stub
		
	}
	

}
