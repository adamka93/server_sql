package Server;
import com.jundev.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class WorkerRunnable implements Runnable {

	private final String HOST = "localhost";
	private final int PORT = 3306;
	private final String DB_NAME = "jundev2";
	private final String USER = "root";
	private final String PSWD = "root";
	
	private Socket clientSocket = null;
	
	public WorkerRunnable(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
	public void run() {
		System.out.println("Thread started.");
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
			String request = br.readLine();
			//br.close();
			
			String[] command = request.split("#");
			System.out.println("REQUEST: " + request);
			executeCommand(command);
			
			
			System.out.println("Client served.");
			clientSocket.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	private void executeCommand(String[] command){
		if( command[0].equals("registration") )
		{
			registration(command[1], command[2], command[3], command[4]);
		}
		else if(command[0].equals("emailCheck"))
		{
			
		}
		else
		{
			System.err.println("Nincs ilyen parancs.");
		}
	}
	
	private Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = null;
		con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME, USER, PSWD);
		return con;
	}
	
	private void registration(String name, String mail, String pswd, String homeLoc) {
		try {
			Connection con = getConnection();
			final String insertQuery = 
					"INSERT INTO user(ID, name, password, email, address) VALUES "
					+ "(NULL, \'" + name + "\', \'"+ pswd +"\', \'"+ mail +"\', \'"+ homeLoc +"\');";
			Statement insterStatement = con.createStatement();
			insterStatement.execute(insertQuery);
			insterStatement.close();
			
			answerToClient("0");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private int getNumRows(ResultSet rs) throws Exception {
		int num = 0;
		while( rs.next() ) {
			num++;
		}
		return num;
	}
	
	private void answerToClient(Object response) {
		try {
			ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
			objectOutput.writeObject( response );
		} catch( IOException ex ) {
			ex.printStackTrace();
		}
	}
	
}
