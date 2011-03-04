package laboration14;

public class Bok extends Media
{
    private String author;


    public Bok( long id, String titel, String author ) { 
        super( id, titel ); // Anrop av Media( long, String );
        this.author = author;
    } 

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String p_author)
    {
        this.author = p_author;
    }

    public String toString()
    {
        return getTitel() + " skriven av " + author;
    }
}
