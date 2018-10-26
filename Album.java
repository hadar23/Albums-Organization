
public class Album {
	// class behaviors
	private String albumName;
	private Song[] songs;
	private int totalAlbumLength;
	private int numSongs;

	public Album(String albumName) {
		setAlbumName(albumName);
		songs = new Song[20];
	}

	// getters
	public String getAlbumName() {
		return this.albumName;
	}

	public Song[] getAlbumSongs() {
		return this.songs;
	}

	public int getAlbumLength() {
		return this.totalAlbumLength;
	}

	public int getNumSongs() {
		return this.numSongs;
	}

	// setters
	public void setAlbumName(String albumName) {
		this.albumName = albumName.substring(0, 1).toUpperCase() + albumName.substring(1).toLowerCase();
	}

	public void addSong(Song newSong) {// add new song by Song object

		this.songs[this.numSongs++] = newSong;
		this.totalAlbumLength += newSong.getSongLength();
	}

	// add new song by Song information
	public void addSong(String songName, String artistName, int minutes, int seconds) {

		Song newSong = new Song(songName, artistName, minutes, seconds);

		this.songs[this.numSongs] = newSong;
		this.numSongs++;
		this.totalAlbumLength += newSong.getSongLength();
	}

	public int isSongExists(String songName) {// is SongExists

		for (int i = 0; i < this.numSongs; i++)
			if (this.songs[i].isSongEqaul(songName)) {
				return i;

			}
		return -1;

	}

	public void sortByArtist() {// sort By Artist
		Song temp = null;

		for (int i = 0; i < this.numSongs; i++) {
			for (int j = i; j < this.numSongs; j++) {
				// if its not the same artist name
				if (this.songs[i].getArtistName().compareToIgnoreCase(this.songs[j].getArtistName()) > 0) {

					temp = this.songs[i];
					this.songs[i] = this.songs[j];
					this.songs[j] = temp;
					// if its the same artist name

				} else if (this.songs[i].isAtristEqaul(this.songs[j].getArtistName())) {
					if (this.songs[i].getSongLength() > this.songs[j].getSongLength()) {
						temp = this.songs[i];
						this.songs[i] = this.songs[j];
						this.songs[j] = temp;
					}
				}
			}
		}
	}

	// toString
	public String toString() {
		String str = "Album Name: " + this.albumName + ", number of Songs: " + this.numSongs
				+ ", Album Length (in seconds): " + this.totalAlbumLength;
		for (int i = 0; i < this.numSongs; i++)
			str += "\r\n" + this.songs[i];
		return str;
	}

}
