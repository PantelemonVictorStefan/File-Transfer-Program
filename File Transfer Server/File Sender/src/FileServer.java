import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class FileServer {

	public static void main(String []Args)
	{
		
		/*FileReader fr;
		try {
			fr = FileReader.getFileReader("HTC_data.rar");
			FileSlice fs;
			fs=fr.readFile();
			//System.out.println(fs.getPayload()[0]);
			
			//fs=null;
			//fs=fr.readFile();
			//System.out.println(fs.getPayload()[0]);
			int i=0;
			while(fs.getFin()==false)
			{
			fs=null;
				fs=fr.readFile();
				System.out.print(i++ +" ");
				//System.gc();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		 try {
			 String path;
			 Scanner sc=new Scanner(System.in);
			 path=sc.nextLine();
				ServerSocket listener = new ServerSocket(8010);
				 while (true) {
		                System.out.println("Waiting client...");
		                Socket socket = listener.accept();
		                System.out.println("Received client...");
		                new ClientThread(socket,path).start();
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
}
