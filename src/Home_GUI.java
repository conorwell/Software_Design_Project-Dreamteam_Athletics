import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Home_GUI extends JFrame{
    JFrame f = new JFrame();

    JButton recBtn = new JButton();

    JTextPane welcome = new JTextPane();

    JButton addW = new JButton();

    JButton vw = new JButton();
    JButton friendButton = new JButton();
    JButton leaderButton = new JButton();
    JButton logout = new JButton();

    JButton statsButton = new JButton();
    public void init(String username){

        f.setSize(400,500);

        welcome.setPreferredSize(new Dimension(300,20));
        welcome.setText("Hello " + username + "!");
        welcome.setAlignmentX(CENTER_ALIGNMENT);
        welcome.setEditable(false);
        f.add(welcome);

        recBtn.setSize(50,40);
        recBtn.setText("Recommender");
        f.add(recBtn);

        addW.setText("Add Workout");
        addW.setSize(50,40);
        f.add(addW);

        vw.setText("View Workouts");
        vw.setSize(50,40);
        f.add(vw);

        friendButton.setText("Friends");
        friendButton.setSize(50,40);
        f.add(friendButton);

        leaderButton.setText("Leaderboard");
        leaderButton.setSize(50,40);
        f.add(leaderButton);

        statsButton.setText("Stats");
        statsButton.setSize(50,40);
        f.add(statsButton);

        logout.setText("Logout");
        logout.setSize(50,40);
        f.add(logout);

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new FlowLayout());


    }

}
