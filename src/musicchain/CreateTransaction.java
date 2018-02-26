package musicchain;

import java.util.Scanner;

public class CreateTransaction 
{
	public boolean InitiateTransaction()
	{
		boolean transaction_complete = false;
		
		Scanner reader = new Scanner(System.in);
		
		//This class will capture what the user has entered and will provide an option to save it to the block
		System.out.println("Enter the Song title: ");
		
		// Capture what the user has entered
		GlobalVariables.data().song_title = reader.nextLine();
		
		//Capture the artiste of the song
		System.out.println("Enter the Song Artiste: ");
		
		// Capture what the user has entered
		GlobalVariables.data().song_artiste = reader.nextLine();
		
		//Request for the user address
		System.out.println("Enter your BITCOIN address: ");
		
		GlobalVariables.data().seller_address = reader.nextLine();
		
		//Request for the user address
		System.out.println("Enter buyer's BITCOIN address: ");
				
		GlobalVariables.data().buyer_address = reader.nextLine();
		
		// Validate the addresses
		ValidateAddress validateAddress  = new ValidateAddress();
		boolean address_is_valid = validateAddress.assertBitcoin(GlobalVariables.data().seller_address, true);
		
		//If the response from the address validation is true, move to the next step
		if(address_is_valid)
		{
			// Validate the buyer's address as well
			boolean buyer_address_is_valid = validateAddress.assertBitcoin(GlobalVariables.data().buyer_address, true);
			
			if(buyer_address_is_valid)
			{
				System.out.println("All addresses are valid");
				transaction_complete = true;
			}
			else
			{
				System.out.println("Buyer's address is invalid.");
				transaction_complete = false;
			}
		}
		else
		{
			System.out.println("You entered an invalid address.");
			transaction_complete = false;
		}

		return transaction_complete;
	}
}
