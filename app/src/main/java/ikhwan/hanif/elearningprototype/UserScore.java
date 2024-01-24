package ikhwan.hanif.elearningprototype;

public class UserScore implements Comparable<UserScore> {

    private String id;
    private String username;
    private int score;

    public UserScore() {
    }

    public UserScore(String id, String username, int score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(UserScore userScore) {
        // Digunakan untuk mengurutkan UserScore dari yang tertinggi ke terendah
        return Integer.compare(this.score, userScore.getScore());
    }

    @Override
    public String toString() {
        // Digunakan untuk menampilkan informasi dalam ListView
        return "Username: " + username + "\nPoint: " + score;
    }
}
