package labB; 
import java.util.*; 
 
public class Album 
{ 
    private ArrayList<Foto> register = new ArrayList<Foto>(); 
     
    public void laggTill(Foto foto) { 
        register.add(foto); 
    } 
     
    public boolean taBort(Foto foto) { 
        return register.remove(foto); 
    } 
     
    public int antalFoto() { 
        return register.size(); 
    } 
 
    public Foto hamtaFoto(int index) { 
        return register.get(index); 
    } 
     
    // Ett alternativt sätt att iterera genom en ArrayList 
    public void listaAlla() { 
        Iterator<Foto> iter = register.iterator(); 
        while(iter.hasNext()) 
            System.out.println(iter.next()); 
    } 
     
    public ArrayList<Foto> getKategori(int kategori) { 
        ArrayList<Foto> res = new ArrayList<Foto>(); 
        Foto foto; 
        for(int i=0; i<register.size(); i++) { 
            foto = register.get(i); 
            if(foto.getKategori() == kategori) 
                res.add(foto); 
        } 
        return res; 
    } 
     
    public Album getArtal(int artal) { 
        Album res = new Album(); 
        Foto foto; 
        for(int i=0; i<register.size(); i++) { 
            foto = register.get(i); 
            if(foto.getArtal() == artal) 
                res.laggTill(foto); 
        } 
        return res; 
    } 
} 
