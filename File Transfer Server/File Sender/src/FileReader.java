import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileReader {

	//private int max=1073741823;
	//private int max=536870911;
	//private int max=268435455;
	private int max=134217727;
	//private int max=5368;
	private int progress;
	boolean fin;
	String path;
	RandomAccessFile f1;
	
	private FileReader(String path)
	{
		progress=0;
		fin=false;
		this.path=path;
		try {
			f1=new RandomAccessFile(path, "rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static FileReader getFileReader(String path) throws FileNotFoundException
	{
		File f=new File(path);
		if(f.exists())
			return new FileReader(path);
		else
			throw new FileNotFoundException();
	}
	
	public  FileSlice readFile()
	{
		int x;
		if(fin)
			return null;
		try {
			if(f1.length()-((long)max*(long)progress)<max)
			{
				x=(int) (f1.length()-max*progress);
				byte []b=null;
				b=new byte[x];
				System.out.println(x);
				f1.read(b, 0,x-1);
				progress++;
				fin=true;
				f1.close();
				return new FileSlice(b,progress==1?true:false,true);
			}
			else
			{
				byte []b=null;
				b=new byte[max];
				//System.out.println((progress*max)+" "+(((progress)*max)));
				//f1.read(b, progress*max,((progress)*max)+1);
				//long l=(f1.length()-(long)(max*progress));
				
				
				f1.read(b,0,max);
				progress++;
				return new FileSlice(b,progress==1?true:false,false);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
