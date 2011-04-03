package laboration14; 

public class CD extends Media
{
    private String artist;
    private String[] melodier;

    public CD(long id, String titel, String artist, String[] melodier)
    {
        super(id, titel);
        this.artist = artist;
        this.melodier = melodier;
    }

    public void setArtist(String p_artist)
    {
        artist = p_artist;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setMelodier(String[] p_melodier)
    {
        melodier = p_melodier;
    }

    public String[] getMelodier()
    {
        return melodier;
    }

    public String toString()
    {
        String returnValue = "Artist: " + artist +
            "\nAlbum: " + getTitel() +
            "\nMelodier:";

        int i = 1;
        for (String melodi : melodier)
        {
            returnValue += "\n" + i + ". " + melodi;
            i++;
        }

        return returnValue;
    }
}
