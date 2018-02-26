package musicchain;

import java.util.Date;

public class DataBlock 
{
	// Create a hash that will hold the digital signature of the block
	public String hash;
	
	//Get the hash of the previous block
	public String previousHash;
	
	//Fetch the block of data
	private String data;
	private long timeStamp;
	private int nonce;

	//Create a Constructor for the block
	public DataBlock(String data,String previousHash) 
	{
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	public String calculateHash() 
	{
		String calculatedhash = HashStrings.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				data 
				);
		return calculatedhash;
	}
	
	public void mineBlock(int difficulty) 
	{
		// Create a string and set it's difficulty to 0
		String target = new String(new char[difficulty]).replace('\0', '0'); 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		System.out.println("Block has been mined successfully and hash is: " + hash);
	}
}