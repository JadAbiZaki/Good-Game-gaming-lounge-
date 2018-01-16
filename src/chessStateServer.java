import java.io.IOException;

public class chessStateServer extends gameStateServer{
	
	String whitePlayer;
	String blackPlayer;
	int threadID1;
	int threadID2;
	int board[][] = new int[8][8];

	public chessStateServer(String player1, String player2, int threadID1, int threadID2) {
		this.whitePlayer = player1;
		this.blackPlayer = player2;
		this.threadID1 = threadID1;
		this.threadID2 = threadID2;
		for (int i = 0; i < 8; i++){
	        	for(int j = 0; j < 8; j++)
	        		board[i][j] = 0;
	        }
		for(int i=0;i<8;i++){
	           board[6][i]=1;
	           board[1][i]=-1;
	       }
	       board[0][0]=-10;board[0][1]=-8;board[0][2]=-6;board[0][3]=-50;board[0][4]=-20;board[0][5]=-6;board[0][6]=-8;board[0][7]=-10;
	       board[7][0]=10;board[7][1]=8;board[7][2]=6;board[7][3]=50;board[7][4]=20;board[7][5]=6;board[7][6]=8;board[7][7]=10;
	}

	

	@Override
	public void updateState(String player, int y, int x) {// not used in chess
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public void updateState(String player, int oldx, int oldy, int newx, int newy, int piece) {
		board[oldx][oldy] = 0;
		board[newx][newy] = piece;
		String xpos = Integer.toString(oldx);
		String ypos = Integer.toString(oldy);
		String intnewx = Integer.toString(newx);
		String intnewy = Integer.toString(newy);
		String intpiece = Integer.toString(piece);
		if(player.equals(whitePlayer)){
					
			try {
				Server.threads[threadID2].dataout.writeBytes("gamemove chess " + xpos + " " + ypos +" "+ intnewx + " " + intnewy+" " + intpiece + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(player.equals(blackPlayer)){;
			try {
				Server.threads[threadID1].dataout.writeBytes("gamemove chess " + xpos + " " + ypos +" "+ intnewx + " " + intnewy+" " + intpiece + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
