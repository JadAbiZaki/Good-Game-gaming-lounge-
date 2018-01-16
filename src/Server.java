

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Server {
	private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private int initialport = 6789;
    private ServerSocket serverSocket;
    public static final ServerThread[] threads = new ServerThread[100];		//maximum threads = 100
    private static int lower = 0;											//lower limit of threads array
    private static int upper = 0;											//upper limit if threads array
    public static gameStateServer games[] = new gameStateServer[100];
    private static int lowerGames = 0;											//lower limit of games array
    private static int upperGames = 0;											//upper limit if games array
    

    public Server() throws ClassNotFoundException {}

    public void acceptConnections() {
        try
        {
            serverSocket = new ServerSocket(initialport);
            
        }
        catch (IOException e)
        {
            System.err.println("ServerSocket instantiation failure");
            e.printStackTrace();
            System.exit(0);
        }

        while (true) {
            try
            {
                Socket newConnection = serverSocket.accept();
                System.out.println("accepted connection");
				//now each client gets a threads that deals with its connection and requests //
                threads[upper] = new ServerThread(newConnection);
                new Thread(threads[upper]).start();
                upper++;
				//now the server will continue waiting for other requests and the current user will be serviced
				// by the created thread //
            } 
            catch (IOException ioe)
            {
                System.err.println("server accept failed");
            }
        }
    }
    
    public static void sendFriends(String username, String userFriends){				//sends list of friends upon login, including status
    	String databaseName = "mydb";								//list will  be sent also when friends list modified
		String databaseUsername = "root";
		String databasePassword = "root";
		
		try{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
				databaseUsername,databasePassword);
		
		String query = "select * from " + userFriends + "friends";
		
		PreparedStatement myStmt = conn.prepareStatement(query);
		ResultSet myRs = myStmt.executeQuery();
		int threadID = getID(username);
		threads[threadID].dataout.writeBytes("frndlist ");
		while (myRs.next())
			threads[threadID].dataout.writeBytes(myRs.getString("username") + " ");
		threads[threadID].dataout.writeBytes("\n");
		}
		
		catch(Exception e){
			
		}
    }

    public static void main(String args[]) {
    	LogManager.getLogManager().reset();
    	logr.setLevel(Level.ALL);
    	try{
    		FileHandler fh = new FileHandler("serverlog.log", true);
    		fh.setLevel(Level.ALL);
    		logr.addHandler(fh);
    	}
    	catch(IOException e){
    		logr.log(Level.SEVERE, "File logger not working", e);
    	}

        Server server = null;
        try {
            server = new Server();
        } catch (ClassNotFoundException e) {
            //   System.out.println("unable to load JDBC driver");
            e.printStackTrace();
            System.exit(1);
            logr.log(Level.SEVERE, "could not call server constructor", e);
        }

        server.acceptConnections();
    }
    
    public static int getID(String Username){								//gets index of threads which has username
    	
    	String databaseName = "mydb";
		String databaseUsername = "root";
		String databasePassword = "root";
	
		try{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
				databaseUsername,databasePassword);
    	String query = "select threadID as ID from userpass where username=?";
    	PreparedStatement myStmt = conn.prepareStatement(query);
		myStmt.setString(1, Username);
		ResultSet myRs1 = myStmt.executeQuery();
		if(myRs1.next()){
		int tempID = myRs1.getInt("ID");
		return tempID;
		}
    	
		}
		catch(Exception e){
			
		}
		return -1;
    }
    
    public static void friendRequest(String newFriend, String clientUsername, DataOutputStream dataout){	//responsible for sending request from clientUsername to friend
        	try {
    			/*Modify these to fit your database name, username, and password*/
        		if(newFriend.equals(clientUsername)){
        			dataout.writeBytes("NOfriend\n");
        		}
    			String databaseName = "mydb";
    			String databaseUsername = "root";
    			String databasePassword = "root";
    			
    			
        		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
        				databaseUsername,databasePassword);
        		
        		String query = "select * from userpass where username = ?";
        		
        		PreparedStatement myStmt = conn.prepareStatement(query);
        		
        		myStmt.setString(1, newFriend);
				
        		int threadID;
        		ResultSet myRs = myStmt.executeQuery();
        		if(myRs.next()){
        			
	        			query = "INSERT INTO " + clientUsername + "friends VALUES ('" + newFriend + "', 'pending', 'nothing')";//user found, will check status
	        			java.sql.Statement insertQuerystmt = conn.createStatement();
	    				insertQuerystmt.executeUpdate(query);
	    				
	    				dataout.writeBytes("OKfriend " + newFriend + "\n");
	    				query = "INSERT INTO " + newFriend + "friends VALUES ('" + clientUsername + "', 'request', 'nothing')";//clientUsername is requesting to be newFriend's friend
	    				insertQuerystmt.executeUpdate(query);
	    				threadID = getID(newFriend);
	    				if(threadID >= 0){
	    					dataout.writeBytes("OKfriend\n");
	    					threads[threadID].dataout.writeBytes("newf " + clientUsername + "\n");		//if user online automatically send friend request
	    				}
        			}
        			
    				//if user not online friends list will be sent on login
        		
            	else{
            		dataout.writeBytes("NOfriend\n");					//user does not exist
            		}
        	}
        	catch(Exception e){
        		
        	}
        }
    	
    public static void exit(String clientUsername){
    	try{
        	String databaseName = "mydb";
			String databaseUsername = "root";
			String databasePassword = "root";
			
			
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
    				databaseUsername,databasePassword);
    		String insertQuery = "UPDATE userpass SET isLoggedIn = FALSE, threadID = NULL WHERE username = '" + clientUsername + "'";
			java.sql.Statement insertQuerystmt = conn.createStatement();
			insertQuerystmt.executeUpdate(insertQuery);	
        	}
        	catch(Exception e){
        	
        	}
    }
    
    public static void challenge(String player1, String player2, String game, DataOutputStream dataout){
    	try {	
			/*Modify these to fit your database name, username, and password*/
			String databaseName = "mydb";
			String databaseUsername = "root";
			String databasePassword = "root";
			
		
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
    				databaseUsername,databasePassword);
    		
    		String query = "select * from "+player1+ "friends where username = ?";		//checking if player in friends list
    		
    		PreparedStatement myStmt = conn.prepareStatement(query);
    		
    		myStmt.setString(1, player2);
			
    		int threadID;
    		
    		ResultSet myRs = myStmt.executeQuery();
    		if(myRs.next()){	
				threadID = getID(player2);
				if(threadID >= 0){
					dataout.writeBytes("challsent\n");
					threads[threadID].dataout.writeBytes("gamechall " + player1 + " " + game + "\n");		//if user online automatically send friend request
				}
				//if user not online challenge cant be sent
				else
					dataout.writeBytes("NOchall\n");
    		}
        	else{
        		dataout.writeBytes("NOfriendlist\n");					//user does not exist in friends list
        		}
    	}
    	catch(Exception e){
    		
    	}
    }
    
    public static void chatWith(String sender, String receiver, String[] message, DataOutputStream dataout){
    	try {	
			/*Modify these to fit your database name, username, and password*/
			String databaseName = "mydb";
			String databaseUsername = "root";
			String databasePassword = "root";
		
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
    				databaseUsername,databasePassword);
    		
    		String query = "select * from "+sender+ "friends where username = ?";		//checking if player in friends list
    		
    		PreparedStatement myStmt = conn.prepareStatement(query);
    		System.out.println(sender + " " + receiver);
    		myStmt.setString(1, receiver);
			
    		int threadID;
 
    		ResultSet myRs = myStmt.executeQuery();
    		
    		if(myRs.next()){    			   			
				threadID = getID(receiver);
				System.out.println(threadID);
				if(threadID >= 0){    		
					threads[threadID].dataout.writeBytes("chat " + sender + " " + receiver + " ");		//if user online automatically send friend requesting
					for(int i = 3; i < message.length; i++)
						threads[threadID].dataout.writeBytes(message[i] + " ");
					threads[threadID].dataout.writeBytes("\n");
				}
				//if user not online challenge cant be sent
				else
					dataout.writeBytes("msgnotsent\n");
    		}
        	else{
        		dataout.writeBytes("NOfriendlist\n");					//user does not exist in friends list
        		}
    	}
    	catch(Exception e){
    		
    	}
    }

    public static void confirmSession(String player2, String player1, String game, DataOutputStream dataout){
    	int threadID1 = getID(player1);
    	int threadID2 = getID(player2);
    	if(game.equals("TTT"))
    		games[upperGames] = new TTTStateServer(player1, player2,threadID1,threadID2);
    	else if(game.equals("chess"))
    		games[upperGames] = new chessStateServer(player1, player2, threadID1, threadID2);
    	try {
    		
    		String strUpper = Integer.toString(upperGames);
    		dataout.writeBytes("gameserverID " + game + " " + strUpper + "\n");
			threads[threadID1].dataout.writeBytes("response acc " + game + " " + strUpper + "\n");
    		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	upperGames++;
    }
    
    public static void abortSession(String player2, String player1, String game){
    	int threadID1 = getID(player1);
    	
    	try {
			threads[threadID1].dataout.writeBytes("response rej " + game + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void confirmFriends(String user1, String user2){
    	try{
    	String databaseName = "mydb";
		String databaseUsername = "root";
		String databasePassword = "root";
	
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
				databaseUsername,databasePassword);
		
    	String query = "UPDATE " + user1 + "friends SET status = 'friends' WHERE username = '" + user2 + "'" ;
    	java.sql.Statement insertQuerystmt = conn.createStatement();
		insertQuerystmt.executeUpdate(query);	
		int threadID = getID(user1);
		threads[threadID].dataout.writeBytes("nowf " + user2 + "\n");
		query = "UPDATE " + user2 + "friends SET status = 'friends' WHERE username = '" + user1 + "'" ;
		insertQuerystmt.executeUpdate(query);	
		threadID = getID(user2);
		threads[threadID].dataout.writeBytes("nowf " + user1 + "\n");
    	}
    	catch(Exception e){
    		
    	}
    }
    
    public static void rejectFriends(String user1, String user2){
    	try{
    	String databaseName = "mydb";
		String databaseUsername = "root";
		String databasePassword = "root";
	
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
				databaseUsername,databasePassword);
		
    	String query = "DELETE FROM " + user1 + "friends WHERE username = '" + user2 + "'" ;
    	java.sql.Statement insertQuerystmt = conn.createStatement();
		insertQuerystmt.executeUpdate(query);	
		query = "DELETE FROM " + user2 + "friends WHERE username = '" + user1 + "'" ;
    	insertQuerystmt = conn.createStatement();
		insertQuerystmt.executeUpdate(query);	
    	}
    	catch(Exception e){
    		
    	}
    }
    
    
    public static void execute(String[] cmd, String user, DataOutputStream dataout){				//execute splitClientCmd. user is user who sent cmd
    	System.out.println(cmd[0]);
    	System.out.println(cmd[1]);
    	if(cmd[0].equals("newf"))
    		friendRequest(cmd[1], user, dataout);
    	else if(cmd[0].equals("accf"))
    		confirmFriends(cmd[1], cmd[2]);
    	else if(cmd[0].equals("rejf"))
    		rejectFriends(cmd[1], cmd[2]);
    	else if(cmd[0].equals("getf"))
    		sendFriends(cmd[1], cmd[2]);
    	else if(cmd[0].equals("remf"))
    		rejectFriends(cmd[1], cmd[2]);
    	else if(cmd[0].equals("exit"))
    		exit(cmd[1]); 
    	else if(cmd[0].equals("game"))
    		challenge(cmd[1], cmd[2], cmd[3], dataout);
    	else if(cmd[0].equals("chat"))
    		chatWith(cmd[1], cmd[2], cmd, dataout);  
    	else if(cmd[0].equals("accresponse"))
    		confirmSession(cmd[1], cmd[2], cmd[3], dataout);
    	else if(cmd[0].equals("rejresponse"))
    		abortSession(cmd[1],cmd[2],cmd[3]);
    	else if(cmd[0].equals("TTT")){
    		int gameIndex = Integer.parseInt(cmd[1]);
    		int posx = Integer.parseInt(cmd[3]);
    		int posy = Integer.parseInt(cmd[4]);
    		games[gameIndex].updateState(cmd[2], posx, posy);
    	}
    	else if(cmd[0].equals("chess")){
    		System.out.println(cmd[0]);
    		System.out.println(cmd[1]);
    		System.out.println(cmd[2]);
    		System.out.println(cmd[3]);
    		System.out.println(cmd[4]);
    		System.out.println(cmd[5]);
    		System.out.println(cmd[6]);
    		System.out.println(cmd[7]);
    		int gameIndex = Integer.parseInt(cmd[1]);
    		int oldx = Integer.parseInt(cmd[3]);
    		int oldy = Integer.parseInt(cmd[4]);
    		int newx = Integer.parseInt(cmd[5]);
    		int newy = Integer.parseInt(cmd[6]);
    		int piece = Integer.parseInt(cmd[7]);
    		games[gameIndex].updateState(cmd[2], oldx, oldy, newx, newy, piece);
    	}
    	else if(cmd[0].equals("img")){}
    	else if(cmd[0].equals("result")){
    		if(cmd[1].equals("TTT")){
    			int gameIndex = Integer.parseInt(cmd[2]);
    			((TTTStateServer) games[gameIndex]).winner(cmd[1]);
    		}
    	}
    }
    
    class ServerThread implements Runnable {

        private Socket socket;
        public BufferedReader datain;
        public DataOutputStream dataout;
        String clientUsername, clientPassword;
        
        

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        public void run()
        {
        	
            try
            {
                datain =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
                dataout = new DataOutputStream(socket.getOutputStream());
                
                
            } 
            catch (IOException e)
            {
            	logr.log(Level.SEVERE, "coud not establish iostreams", e);
                return;
                
            }
            //work
           
            try 
            {
            	
            	String clientCmd = datain.readLine();
            	if(clientCmd.equals("auth")){
            	
	        		clientUsername = datain.readLine();
	        		clientPassword = datain.readLine();
	        		
	        		
	        		
	        		
	        		try {
	        			/*Modify these to fit your database name, username, and password*/
	        			String databaseName = "mydb";
	        			String databaseUsername = "root";
	        			String databasePassword = "root";
	        			
	        			
		        		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
		        				databaseUsername,databasePassword);
		        		
		        		String query = "select password as pass, isLoggedIn as status from userpass where username=?";
		        		
		        		PreparedStatement myStmt = conn.prepareStatement(query);
		        		
		        		myStmt.setString(1, clientUsername);
		        		
		        		ResultSet myRs = myStmt.executeQuery();
		        		
		        		
		        		if(myRs.first()) {
		        			String myPass = myRs.getString("pass");
		        			boolean status = myRs.getBoolean("status");
		        			if(status){
		        				dataout.writeBytes("online");
		        			}
		        			else{
		        			if(clientPassword.equals(myPass)){
		        				dataout.writeBytes("Found\n");	    
		        				int tempUpper = upper - 1;			//the threadID is always upper - 1
		        				String insertQuery = "UPDATE userpass SET isLoggedIn = TRUE, threadID = " + tempUpper + " WHERE username = '" + clientUsername + "'";
		        				java.sql.Statement insertQuerystmt = conn.createStatement();
		        				insertQuerystmt.executeUpdate(insertQuery);	
		        				boolean exit = false;
		                		while(((clientCmd = datain.readLine())!=null)&&(exit != true)){
		                			String[] splitClientCmd = clientCmd.split(" ");
		                			execute(splitClientCmd, clientUsername, dataout);
		                		}
		        			}
		            		else
		            			dataout.writeBytes("Not Found\n");
		        			}
		            		
		        		} else {
	            			dataout.writeBytes("Not Found\n");
		        		}
		        		
	        		
	        		
	        		
	        		
        		} catch(Exception e) {
        			logr.log(Level.SEVERE, "database access error", e);
        		}
        		
                try {
                     System.out.println("closing socket");
                     datain.close();
                     dataout.close();
                     socket.close();
                 } 
                 catch (IOException e)
                 {
                 	logr.log(Level.SEVERE, "socket or iostream would not close", e);
                 }
        		 
        		 
            }
            else if(clientCmd.equals("reg")){
        		clientUsername = datain.readLine();
        		clientPassword = datain.readLine();
        	String 	clientEmail = datain.readLine();
        	System.out.println(clientEmail);
        	System.out.println(clientPassword);
        	System.out.println(clientUsername);
        		
        		
        		try {
        			/*Modify these to fit your database name, username, and password*/
        			String databaseName = "mydb";
        			String databaseUsername = "root";
        			String databasePassword = "root";
        			
        			
	        		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
	        				databaseUsername,databasePassword);
	        		
	        		String query = "select * from userpass where username = ?";
	        		
	        		PreparedStatement myStmt = conn.prepareStatement(query);
	        		
	        		myStmt.setString(1, clientUsername);
	        		
	        		ResultSet myRs = myStmt.executeQuery();
	        		
	        		
	        		if(myRs.next()) 
	        			dataout.writeBytes("Taken\n");
	            	else{
	            		dataout.writeBytes("OK\n");
	            		System.out.println("lelel");
	            		query = "INSERT INTO userpass(username,password,isLoggedIn,email) VALUES ('" + clientUsername + "', '" + clientPassword + "', '0', '"+clientEmail+"')";
	            		
	            		java.sql.Statement stmt = conn.createStatement();
	            		stmt.executeUpdate(query);	 
	            		query = "CREATE TABLE " + clientUsername + "Friends(username varchar(20),status varchar(20), tictactoeWins int(20),tictactoeLosses int(20), tictactoeDraws int(20),chessWins int(20),chessLosses int(20),sudokuWins int(20),sudokuLosses int(20))";
	            		stmt.executeUpdate(query);
	            		}
	            		
	        	
	        		
	        		
        		} catch(Exception e) {
        			logr.log(Level.SEVERE, "database access error", e);
        		}
        		 try
                 {
                     System.out.println("closing socket");
                     datain.close();
                     dataout.close();
                     socket.close();
                 } 
                 catch (IOException e)
                 {
                 	logr.log(Level.SEVERE, "socket or iostream would not close", e);
                 }
            }
            else if(clientCmd.equals("exit")){
            	clientUsername = datain.readLine();
            	try{
            	String databaseName = "mydb";
    			String databaseUsername = "root";
    			String databasePassword = "root";
    			
    			
        		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
        				databaseUsername,databasePassword);
        		String insertQuery = "UPDATE userpass SET isLoggedIn = FALSE WHERE username = '" + clientUsername + "'";
				java.sql.Statement insertQuerystmt = conn.createStatement();
				insertQuerystmt.executeUpdate(insertQuery);	
            	}
            	catch(Exception e){
            	
            	}
            	
            }
            else if(clientCmd.equals("chat")){
            	clientUsername = datain.readLine();
            	String chatWith = datain.readLine();
            	String databaseName = "mydb";
    			String databaseUsername = "root";
    			String databasePassword = "root";
    			
    			try{
        		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName,
        				databaseUsername,databasePassword);
        		
        		String query = "select isLoggedIn as status from userpass where username=?";
        		
        		PreparedStatement myStmt = conn.prepareStatement(query);
        		
        		myStmt.setString(1, chatWith);
        		
        		ResultSet myRs = myStmt.executeQuery();
        		
        		if(myRs.first()) {
        			boolean status = myRs.getBoolean("status");
        			if(status)
        				dataout.writeBytes("yes\n");
        			else
        				dataout.writeBytes("no\n");            		
        		}
        		else {
        			dataout.writeBytes("Not Found\n");
        		}
    			}
    			catch(Exception e){
    				
    			}
    			int threadID = 0;					//determines index of thread to chat with
    			for(int i = lower; i <= upper; i++){
    				try{
    				if(threads[i].clientUsername.equals(chatWith)){
    					threadID = i;
    					break;
    				}
    				}
    				catch(Exception e){
    					continue;
    				}
    			}
    			threads[threadID].dataout.writeBytes("chat " + clientUsername);
    			while(true){
    				threads[threadID].dataout.writeBytes(datain.readLine());
    				dataout.writeBytes(threads[threadID].datain.readLine());
    			}
            }
            	/*This command is for both sending and confriming friend requests
            	 * this command will first check if the newFriend had sent a friend request to the client that sent this cmd
            	 * if so, then the request will be accepted. Else the request will be sent
            	 */
            
            	
//            else if(clientCmd.equals("newf")){
//            	friendRequest();
//            }
                   		
        		
        		
        		
        		
        	   
            } catch (IOException k)
            {
                System.out.println(k);
                logr.log(Level.SEVERE, "could not read command", k);
            }
            
        }
    }
}
