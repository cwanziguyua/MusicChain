package musicchain;

import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.util.Scanner;

public class MusicChain 
{
	public static ArrayList<DataBlock> blockchain = new ArrayList<DataBlock>(); 
	public static int difficulty = 0;
	
	public static void main(String[] args) 
	{
		// First ask the user to select an option. 
		Scanner reader = new Scanner(System.in);
		int selected_option, user_option;
		
		//Display the options for the user to choose. 
		System.out.println("To get started, please select an option from the ones below.");
		System.out.println("Add a transaction to the existing block  (1).");
		System.out.println("Mine the entire block                    (2).");
		System.out.println("Verify a transaction                     (3).");
		System.out.println("View entire block                        (4).");
		System.out.println("");
		System.out.println("-------------------------------------------------------------");		
		
		System.out.println("Select an option to get started: ");
		selected_option = reader.nextInt();
		
		//From what the user has entered, assign it to the global variable so that it is accessible to the other classes as well
		GlobalVariables.data().user_option = selected_option;
		
		// Print out what the user has entered 
		System.out.println("The number you entered is: "+GlobalVariables.data().user_option);
		
		//Check what option the user has selected and call the relevant class.
		if(GlobalVariables.data().user_option == 1)
		{
			//Call the class to create a new transaction
			//Create constructor for classes to be called
			CreateTransaction createTransaction = new CreateTransaction();		
			boolean transaction_created = createTransaction.InitiateTransaction();
			
			if(transaction_created)
			{
				System.out.println("Transaction created successfully.");
			}
			else
			{
				System.out.println("Unable to complete transaction.");
				
				//Ask the user if they want to still create a transaction
				System.out.println("Press 0 to exit.");
				
				user_option = reader.nextInt();
				if(user_option == 0)
				{
					//Exit the application
					System.exit(0);
				}
				else
				{
					//Display an error
					System.out.println("You entered an invalid input.");
				}
				
			}
		}
		
		// Close the reader once the data has been extracted
		reader.close();
		
		/*blockchain.add(new DataBlock("This is the first block ... ", "0"));
		System.out.println("Trying to Mine the first block ... ");
		blockchain.get(0).mineBlock(difficulty);
		
		// Get the next bloxk and append the previous transaction
		blockchain.add(new DataBlock("This is the second block ...",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine the second block ... ");
		blockchain.get(1).mineBlock(difficulty);
		
		
		blockchain.add(new DataBlock("This is the third block",blockchain.get(blockchain.size()-1).hash));
		System.out.println("Trying to Mine the third block... ");
		blockchain.get(2).mineBlock(difficulty);	
		
		// Display a true or false if the blockchain is valid or not
		System.out.println("\n Blockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);	*/
	}
	
	// This method returns true or false
	public static Boolean isChainValid() 
	{
		// To check the validity of a block, we need to analyze the previous and current blocks first
		DataBlock currentBlock; 
		DataBlock previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) 
		{
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			//Calculate the two hashes to confirm if they are the same 
			if(!currentBlock.hash.equals(currentBlock.calculateHash()))
			{
				System.out.println("Current Hashes are not the same");			
				return false;
			}
			
			// Make a comparison of the hash of previous block and the current one
			if(!previousBlock.hash.equals(currentBlock.previousHash))
			{
				System.out.println("Previous Hashes not equal");
				return false;
			}
			
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget))
			{
				System.out.println("This block has not yet been mined");
				return false;
			}
		}
		return true;
	}
	
}
