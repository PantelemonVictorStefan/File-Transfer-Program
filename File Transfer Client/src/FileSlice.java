import java.io.Serializable;

public class FileSlice implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private byte []payload;
private boolean bgn;
boolean req;
private boolean fin;
String path;
FileSlice(byte[] bi,boolean b,boolean f)
{
	setPayload(bi);
	bgn=b;
	fin=f;
}

FileSlice(boolean req)
{
	this.req=req;
}

public byte [] getPayload() {
	return payload;
}
public void setPayload(byte [] payload) {
	this.payload = payload;
}

void showbin()
{
	System.out.println(bgn+" "+fin+" "+payload.length);
	for(int i=0;i<payload.length;i++)
		System.out.print(payload[i]+" ");
}

void sayHello()
{
	System.out.println("Hello");
}

boolean getFin()
{
	return fin;
}
}
