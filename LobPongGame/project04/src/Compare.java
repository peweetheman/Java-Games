import java.util.Comparator;

public class Compare implements Comparator<Score> {
        public int compare(Score one, Score two) {

            int score1 = one.getScore();
            int score2 = two.getScore();

            if (score1 > score2){
                return -1;
            }else if (score1 < score2){
                return 1;
            }else{
                return 0;
            }
        }
}