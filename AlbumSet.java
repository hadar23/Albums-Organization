
public class AlbumSet {
	String owner;
	Album[] albums;
	int numAlbums = 0;

	public AlbumSet(String owner) {
		this.owner = owner.substring(0, 1).toUpperCase() + owner.substring(1).toLowerCase();
		albums = new Album[20];
	}

	// getters
	public String getOwner() {
		return this.owner;
	}

	public int getNumAlbums() {
		return this.numAlbums;
	}

	public Album getOneAlbumByIndex(int index) {
		return this.albums[index];
	}

	public void addSongToAlbum(String albumName, String songName, String artistName, int minutes, int seconds) {
		// make new song object
		Song newSong = new Song(songName, artistName, minutes, seconds);//
		if (isAlbumExists(albumName))// if is true
			this.albums[getAlbumIndex(albumName)].addSong(newSong);
		else // make new album object
		{

			Album albumName1 = new Album(albumName);
			albumName1.addSong(newSong);
			this.albums[this.numAlbums++] = albumName1;
		}
	}

	public Boolean isAlbumExists(String ablumName) {// if is AlbumExists
		for (int i = 0; i < this.numAlbums; i++) {
			if (this.albums[i].getAlbumName().equalsIgnoreCase(ablumName))
				return true;
		}
		return false;
	}

	public int getAlbumIndex(String albumName) {// getAlbumIndex
		for (int i = 0; i < this.numAlbums; i++)
			if (this.albums[i].getAlbumName().equalsIgnoreCase(albumName))
				return i;

		return -1;// its will never be null bec you said the album is exsist
	}

	public void sortByAlbumsName() {// sortByAlbumsName
		Album temp = null;

		for (int i = 0; i < this.numAlbums; i++) {
			for (int j = i; j < this.numAlbums; j++) {
				if (this.albums[i].getAlbumName().compareToIgnoreCase(this.albums[j].getAlbumName()) > 0) {
					temp = this.albums[i];
					this.albums[i] = this.albums[j];
					this.albums[j] = temp;
				}
			}
		}
	}

	public String toString() {
		String str = "Album owner : " + this.getOwner() + ", number of Album: " + this.getNumAlbums();

		return str;
	}
}
