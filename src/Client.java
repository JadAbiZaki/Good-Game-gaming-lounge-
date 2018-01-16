import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;

import javax.imageio.ImageIO;



public class Client{
	private static int port;
	public static boolean isLoggedin;
	public static String user;

	public Client() throws ClassNotFoundException {}
	private static int lower = 0;											//lower limit of threads array
    private static int upper = 0;											//upper limit if threads array
	
	
	
	public static void chat(String chatWith, String clientUsername, String message){						//chatWith is the username of the person we are chatting with
		try{System.out.println("yo");
			System.out.println(chatWith + " " + clientUsername);
			MyFrame.dataout.writeBytes("chat " + clientUsername + " " + chatWith + " " +  message +"\n");
		}
		catch(Exception e){
			
		}
	}
	
	public static void addFriend(String newFriend, String clientUsername){						//adds a new friend
		try{
			MyFrame.dataout.writeBytes("newf " + newFriend + " " + clientUsername   +"\n");
		}
		
		catch(Exception e){
						
		}
	}
	public static void confirmFriend(String newFriend, String clientUsername){						//adds a new friend
		try{
			MyFrame.dataout.writeBytes("accf " + newFriend + " " + clientUsername   +"\n");
		}
		
		catch(Exception e){
						
		}
	}
	public static void rejectFriend(String newFriend, String clientUsername){						//adds a new friend
		try{
			MyFrame.dataout.writeBytes("rejf " + newFriend + " " + clientUsername   +"\n");
		}
		
		catch(Exception e){
						
		}
	}
	
	public static void getFriends(String clientUsername, String userFriends){						//adds a new friend
		try{
			MyFrame.dataout.writeBytes("getf " +  clientUsername + " " + userFriends  +"\n");
		}
		
		catch(Exception e){
						
		}
	}
	
	public static void removeFriend(String clientUsername, String friend){
		try{
			MyFrame.dataout.writeBytes("remf " +  clientUsername + " " + friend  +"\n");
		}
		
		catch(Exception e){
						
		}
	}
	
	public static void updatePic(String clientUsername, BufferedImage pic){
		try {
			MyFrame.dataout.writeBytes("img " + clientUsername);
			ImageIO.write(pic,"JPG",MyFrame.dataout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void TTTwinner(String player, String gameserverID){
		try{
			MyFrame.dataout.writeBytes("result TTT " + player + " " + gameserverID + "\n");
		}
		catch(Exception e){
			
		}
	}
	
	public static void requestProfile(String user, String profile){
		try{
			MyFrame.dataout.writeBytes("profile " + user + " " + profile + "\n");
		}
		catch(Exception e){
			
		}
	}
		
	
	public static void main(String[] args){
		//Scanner fromconsole = new Scanner(System.in);
		String[] fullcmd = new String[3];							//will hold each word of input
		
		Client client = null;
		try{
		client = new Client();
		}
		catch(Exception e){
			
		}
		while(true){		
			boolean response;
			try{
				getCommand(fullcmd);
				switch (fullcmd[0]){
					case "help" : System.out.println("Type auth x y to login with username x and password y\nType reg x y to register with username c and password y");
								  break;
					case "exit" : //Client.Exit();
								  break;
					case "auth" :Socket clientSocket = new Socket("localhost",6789);
					
								BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
								DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//								char[] pass = fullcmd[2].toCharArray();
//								  response = Authenticate(fullcmd[1], pass);
//								  if(response){
//									  System.out.println("Welcome " + fullcmd[1]);
//								  }
//								  else
//									  System.out.println("Invalid Username or Password");
								  break;
					case "reg" : char[] pass1 = fullcmd[2].toCharArray();
								 //response = register(fullcmd[1], pass1);
								 //if(response)
									// System.out.println("Successfully Registered");
							//	 else
								//	 System.out.println("Username already taken");
								 //break;
					default : System.out.println("Please enter a valid command");
							
				}
				
		}
			catch(Exception e){
				
			}
		}
	}
	
	
	
	public static void getCommand(String[] split){		//takes as input the command plus arguments
		Scanner fromconsole = new Scanner(System.in);
		String command = fromconsole.nextLine();
		String[] temp = command.split(" ");
		for(int i = 0; i < temp.length ; i++)
			split[i] = temp[i];		
	}
	
	public static void receiveResponse(String[] response, Socket clientSocket, BufferedReader inFromServer, DataOutputStream outToServer){
		int i = 0;
		try {
			while((response[i] = inFromServer.readLine()) != null){
				System.out.println(response[i]);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendRequest(String[] request,Socket clientSocket, BufferedReader inFromServer, DataOutputStream outToServer ){					//sends request and receives response
		for(int i = 0; i < request.length; i++){
			try {
				outToServer.writeBytes(request[i] + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			outToServer.writeBytes("fin\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void Exit(String user){						//after logging in
		String cmd = "exit";
		//Socket clientSocket;
		try {
			
			//clientSocket = new Socket("localhost",6789);
			
			
			//DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			MyFrame.dataout.writeBytes(cmd + " " + user +"\n");
			//outToServer.writeBytes(user + "\n");
		}
		catch(Exception e){
		}
		
	}
	public static boolean Authenticate(String username, char[] password) {
		String cmd = "auth";	// to tell the server what to do
		String response;
		
		
		user = username;
		
		
		try {
//			socket = new Socket("localhost",6789);
//			
//			inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			outToServer = new DataOutputStream(socket.getOutputStream());
			String pass = new String(password);
			System.out.println(username);
			System.out.println(password);
			MyFrame.dataout.writeBytes(cmd+ "\n");
			MyFrame.dataout.writeBytes(username +"\n");
			MyFrame.dataout.writeBytes(pass +"\n");
			response = MyFrame.datain.readLine();
			
			if(response.equals("Found")){	
				isLoggedin = false;
				return true;
			}
			else if(response.equals("online")){
				isLoggedin = true;
				return true;				//wont matter; only for nested loops priority
			}		
			
			
			
		} catch (Exception e) {
			
		}
		
		return false;
	}
	
	public static boolean register(String username, char[] password, String email){
		String cmd = "reg";
		
		String response;
		
		Socket clientSocket;
		
		
		try {
			
			clientSocket = new Socket("localhost",6789);
			
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			String pass = new String(password);
			outToServer.writeBytes(cmd + "\n");
			outToServer.writeBytes(username +"\n");
			outToServer.writeBytes(pass +"\n");
			outToServer.writeBytes(email+"\n");
			
			response = inFromServer.readLine();
			
			if(response.equals("OK"))
				return true;
			
			clientSocket.close();
		} catch (Exception e) {
			
		}
		
		return false;
	}
	public static void challenge(String player1, String player2, String game){
		try{
			MyFrame.dataout.writeBytes("game " + player1 + " " + player2 + " " + game + "\n");
		}
		catch(Exception e){
			
		}
	}
	
	public static void respondChallenge(String player1, String player2, String game, boolean response){
		try{
			if(response)
				MyFrame.dataout.writeBytes("accresponse " + player2 + " " + player1 + " "+ game + "\n");
			else
				MyFrame.dataout.writeBytes("rejresponse " + player2 + " " + player1 + " "+ game + "\n");
		}
		catch(Exception e){
			
		}
	}
	

}
	

