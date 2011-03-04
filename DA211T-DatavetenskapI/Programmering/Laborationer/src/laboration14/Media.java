package laboration14;
 
public class Media
{ 
    private long id; 
    private String titel; 
 
    // 1d
    public Media(long id, String titel)
    {
        this.id = id;
        this.titel = titel;
    }


    public long getId()
    { 
        return id; 
    } 
     
    public void setId( long id )
    { 
        this.id = id; 
    } 
 
    public String getTitel()
    { 
        return titel; 
    } 
 
    public void setTitel(String titel)
    { 
        this.titel = titel; 
    } 
     
    public String toString()
    { 
        return "Media: ID = " + this.id + ", Titel = " + this.titel; 
    } 
} 
