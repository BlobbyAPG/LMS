import java.util.ArrayList;
public class GenreCatalog {
    public ArrayList<Genre> genreCatalog = new ArrayList<>();
    public void removeGenre(GenreCatalog gCat, String query){
        ArrayList<Genre> genres = searchForGenre(gCat, query);
        Genre genre = genres.get(0);
        genreCatalog.remove(genre);
        System.out.println(genre.getName() + " removed successfully.");
    }
     public void addGenre(Genre genre){
        genreCatalog.add(genre);
         System.out.println(genre.getName() + " added successfully!");
     }
    public void updateGenre(GenreCatalog gCat, String query) {
        ArrayList<Genre> genres = searchForGenre(gCat, query);
        genre.setName(newName);
        genre.setDescription(newDesc);
    }
    public ArrayList<Genre> searchForGenre(GenreCatalog gCat, String query) {
        ArrayList<Genre> results = new ArrayList<>();
        for (Genre genre : gCat.getGenreCatalog()){
            if (genre.getName().contains(query) || genre.getDescription().contains(query)){
                results.add(genre);
            } else {
                throw new IllegalArgumentException("Sorry, can't find what you're looking for.");
            }
        }
        return results;
    }

    public ArrayList<Genre> getGenreCatalog() {
        return genreCatalog;
    }

    public void setGenreCatalog(ArrayList<Genre> genreCatalog) {
        this.genreCatalog = genreCatalog;
    }

    @Override
    public String toString() {
        return "GenreCatalog{" +
                "genreCatalog=" + genreCatalog +
                '}';
    }
}
