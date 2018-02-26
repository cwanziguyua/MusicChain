package musicchain;

public class GlobalVariables 
{    
    private volatile static GlobalVariables shareData;
    public static GlobalVariables data()
    {
        if(shareData == null)
        {
            synchronized (GlobalVariables.class) 
            {
                if (shareData == null)
                {
                    shareData = new GlobalVariables();
                }
            }
        }
        return shareData;
    }
    
    public int user_option;
    public String transaction_nonce;
    public String song_title;
    public String song_artiste;
    public String previous_block_hash;
    public String seller_address;
    public String buyer_address;
}
