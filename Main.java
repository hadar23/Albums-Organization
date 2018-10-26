
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// phase A
		// main program variables
		// ----------------------
		AlbumSet myAlbums = new AlbumSet("Hadar ");
		// getting file name and open it
		String pathfie = JOptionPane.showInputDialog("please enter input file name");
		// File f = new File(pathfie);
		Scanner s = new Scanner(new File(pathfie));

		// reading input file and creating the albums

		String songName = null, atristName = null, songLength, albumname = null;
		int minutes, seconds;
		while (s.hasNextLine()) {// if we have information
			String str = s.nextLine();// read the next string till the enter
			String[] token = str.split(";");
			if (token.length != 4) {// thats because our last rows are only with a space
				break;
			}
			// scanner the info and match
			songName = token[0].trim().replaceAll("\\s+", " ");
			atristName = token[1].trim().replaceAll("\\s+", " ");
			songLength = token[2].trim().replaceAll("\\s+", " ");
			albumname = token[3].trim().replaceAll("\\s+", " ");
			String[] token1 = songLength.split(":");
			minutes = Integer.parseInt(token1[0].trim().replaceAll("\\s+", " "));
			seconds = Integer.parseInt(token1[1].trim().replaceAll("\\s+", " "));

			myAlbums.addSongToAlbum(albumname, songName, atristName, minutes, seconds);

		}
		s.close();

		// phase B
		// -------
		// album and song ordering
		myAlbums.sortByAlbumsName();

		for (int i = 0; i < myAlbums.getNumAlbums(); i++)
			myAlbums.getOneAlbumByIndex(i).sortByArtist();

		// phase C
		// -------
		// statistical information

		System.out.println("Number of Albums: " + myAlbums.getNumAlbums());
		for (int i = 0; i < myAlbums.getNumAlbums(); i++) {
			System.out.println("Album Name: " + myAlbums.getOneAlbumByIndex(i).getAlbumName() + ", number of Songs: "
					+ myAlbums.getOneAlbumByIndex(i).getNumSongs() + ", Album Length (in seconds): "
					+ myAlbums.getOneAlbumByIndex(i).getAlbumLength());

		}
		// phase D
		// -------
		// searching for a song
		int check = 0;
		int reply = JOptionPane.showConfirmDialog(null, "Do you want to search a song?", "searching",
				JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {// if yes
			String yoursong = JOptionPane.showInputDialog("please enter the name of the song");
			for (int i = 0; i < myAlbums.getNumAlbums(); i++) {
				int songplace = myAlbums.getOneAlbumByIndex(i).isSongExists(yoursong);
				if (songplace != -1) {// if we find the song
					System.out.println("\n" + "The song " + yoursong + " exist. " + "The name of the album is "
							+ myAlbums.getOneAlbumByIndex(i).getAlbumName() + ". The index is " + (songplace + 1));
					check++;
				}
			}
			if (check == 0)// if we didnt find the song
				System.out.println("\n" + "The song " + yoursong + " does not exist");

		}

		// phase E
		// ----------
		// getting output file name and write it and save..

		String outputFilePath = JOptionPane.showInputDialog("Please enter output file name");
		PrintWriter pw = new PrintWriter(outputFilePath);
		String t = "total number of Albums " + myAlbums.getNumAlbums();
		pw.println(t);
		for (int i = 0; i < myAlbums.getNumAlbums(); i++) {
			String tt = "\r\n" + myAlbums.getOneAlbumByIndex(i).toString();
			pw.println(tt);
		}

		pw.close();

	}

}
