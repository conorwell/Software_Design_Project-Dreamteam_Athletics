import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class LeaderBoardGUI {
    JFrame f = new JFrame();
    GridBagConstraints gbc = new GridBagConstraints(); //Layout for gui
    public ArrayList<ArrayList<String>> data;

    public void init(User u){
        //f.setSize(400,500);
        f.setLayout(new GridBagLayout());
        gbc.insets =new Insets(5,5,5,5);

        JLabel title = new JLabel("Leaderboard"); //title for page
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        f.add(title, gbc);
        ArrayList<String> columnNames = new ArrayList<String>(Arrays.asList("user","name","com","dur","date","exer","exer_dur"));

        LeaderModel m = new LeaderModel();
        ArrayList<ArrayList<String>> friendWorkouts = m.getFriendWorkouts(u.getUsername(),this);
        System.out.println(friendWorkouts);
        ArrayList<ArrayList<String>> workouts = m.getOwnWorkouts(u.getUsername(),this);
        DefaultTableModel defTab = new DefaultTableModel();
        defTab = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) { //declaring table is not editable
                //all cells false
                return false;
            }
        };

        for (int i = 0; i < columnNames.size(); i++){
            defTab.addColumn(columnNames.get(i));
        }

        for (int i = 0; i < workouts.size(); i++){
            Vector row = new Vector();
            row.add(u.getUsername());
            for(int j = 0; j<workouts.get(i).size(); j++){
                row.add(workouts.get(i).get(j));
            }
            defTab.addRow(row);
        }

        for (int i = 0; i < friendWorkouts.size(); i++){
            Vector row = new Vector();
            for(int j = 0; j<friendWorkouts.get(i).size(); j++){
                row.add(friendWorkouts.get(i).get(j));
            }
            defTab.addRow(row);
        }


        JTable tab = new JTable(); //adding table
        tab.setModel(defTab);
        f.add(tab);

        JScrollPane scroll = new JScrollPane(); //scroll plane that displays table
        scroll.getViewport().add(tab);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        f.add(scroll,gbc);

        f.setVisible(true);
        f.setTitle("Leaderboard");
        f.setDefaultCloseOperation(2);
        f.pack();
        f.setLocationRelativeTo(null);

    }
}
