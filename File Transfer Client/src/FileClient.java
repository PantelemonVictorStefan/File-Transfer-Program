import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;

public class FileClient {
	//private final static String SERVER_ADDRESS = "127.0.0.1";
	private final static String SERVER_ADDRESS = "192.168.0.7";
	//private final static String SERVER_ADDRESS = "79.115.81.124";
	//private final static String SERVER_ADDRESS = "95.76.0.71";
	
    //private final static int PORT = 8100;
	private final static int PORT = 8010;
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub

		
		 Socket socket=null;
		    //BufferedReader in=null;
	       socket=new Socket(SERVER_ADDRESS,PORT);
	       
	        System.out.println("started");
	       
	        ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
	        ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
	        try {
	        	
				FileSlice fs=(FileSlice) in.readObject();
				RandomAccessFile f1 = new RandomAccessFile(fs.path, "rw");
				//fs.showbin();
				//fs.sayHello();
				int progress=1;
				while(fs.getFin()==false)
				{
					f1.write(fs.getPayload());
					System.out.println(progress++ +" ");
					fs=null;
					out.writeObject(new FileSlice(true));
					fs=(FileSlice) in.readObject();
					//System.gc();
					
					//Thread.sleep(100);
				}
				f1.write(fs.getPayload());
				f1.close();
				in.close();
				System.out.println("done");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        socket.close();
	}
	
}
