import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread implements Runnable {

	Socket client;
	String path;
	
	ClientThread(Socket s,String path)
	{
		client=s;
		this.path=path;
	}
	
	public void run()
	{
		System.out.println("I'm running boss");
		try {
			FileReader fr=FileReader.getFileReader(path);
			ObjectOutputStream out=new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream in=new ObjectInputStream(client.getInputStream());
			FileSlice fs;
			fs=fr.readFile();
			fs.path=path;
			
			//fs.showbin();
			//PrintWriter out=new PrintWriter(client.getOutputStream());
			
			//out.print(fs);
			//out.flush();
			//out.close();
			FileSlice fs2;
			int i=1;
			while(fs.getFin()==false)
			{
				out.writeObject(fs);
				out.flush();
				out.reset();
				//System.out.print(i++ +" ");
				fs=null;
				//System.gc();
				//Thread.sleep(10000);
				fs2=((FileSlice)in.readObject());
				System.out.print(i++ +" ");
				fs2=null;
				fs=fr.readFile();
			}
			out.writeObject(fs);
			out.flush();
			
			out.close();
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			FileReader fr=FileReader.getFileReader("img.jpg");
			FileSlice fs=fr.readFile();
			//fs.showbin();
			PrintWriter out=new PrintWriter(client.getOutputStream());
			out.print(fs);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
}
