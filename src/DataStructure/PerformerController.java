package DataStructure;

import DataStructure.Data.Artist;
import DataStructure.Data.Band;
import DataStructure.Data.Performer;

import java.util.ArrayList;

public class PerformerController {
    private ArrayList<Performer> performers = new ArrayList<>();
    private ArrayList<Artist> artists = new ArrayList<>();
    private ArrayList<Band> bands = new ArrayList<>();

    public void addArtist(String performername) {
        Artist newArtist = new Artist(performername);
        performers.add(newArtist);
        artists.add(newArtist);
    }

    public ArrayList<Performer> getPerformers() {
        return this.performers;
    }

    public void addBandMember(String performerName) {
        Artist newArtist = new Artist(performerName);
        artists.add(newArtist);
    }

    public boolean artistAlreadyExists(String performerName){
        for (Artist artist : artists) {
          if(artist.getName().equals(performerName)){
              return true;
          }
        }
        return false;
    }

    public boolean performerAlreadyExists(String performerName){
        for (Performer performer : performers) {
            if(performer.getPerformerName().equals(performerName)){
                return true;
            }
        }
        return false;
    }

    public void addBand(String performerName) {
        Band newBand = new Band(performerName);
        performers.add(newBand);
        bands.add(newBand);
    }

    public void updatePerformer() {

    }
}
