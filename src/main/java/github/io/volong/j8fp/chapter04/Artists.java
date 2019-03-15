package github.io.volong.j8fp.chapter04;

import github.io.volong.j8fp.Artist;
import org.apache.zookeeper.Op;

import java.util.List;
import java.util.Optional;

public class Artists {
    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    public Optional<Artist> getArtist(int index) {
        if (index < 0 || index >= artists.size()) {
            return Optional.empty();
        }

        return Optional.of(artists.get(index));
    }

    private void indexException(int index) {
        throw new IllegalArgumentException(index +
                "doesn't correspond to an Artist");
    }

    public String getArtistName(int index) {
        return getArtist(index).map(Artist::getName).orElse("unknown");
    }
}