import java.io.Serializable;

class Score  implements Serializable, Comparable<Score>{    
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public String getname() {
        return name;
    }

    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }    
    
    @Override
    public int compareTo(Score score1) {              
        return ((Integer)(score1.getScore())).compareTo(getScore());
    }
}