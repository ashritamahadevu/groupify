import java.awt.*;
import java.util.List;
import javax.swing.JPanel;

public class Circle extends JPanel {
    //a list that holds student groups
    private List<List<Student>> groups;

    //initializes the window and sets up the groups
    public Circle(List<List<Student>> groups) 
    {
        this.groups = groups;
        setSize(1100, 600); //sets window size
        setBackground(Color.WHITE); //sets backround color to white
        setVisible(true); //makes panel visible
    }

    public void paintComponent(Graphics window) 
    {
        super.paintComponent(window);
        window.setFont(new Font("TAHOMA", Font.BOLD, 14));
        
        //starting x-position for drawing circles
        int xPos = 110; 
        //starting y-position for drawing circles
        int yPos = 150; 
        int radius = 80;
        // horizontal gap between circles
        int circleGap = 200; 
        
        for (int i=0; i<groups.size(); i++) 
        {
            // Determines x and y position for each circle
            int x = xPos + (i%3) * circleGap; 
            // moves circle to next row after first 3 circles
            int y = yPos + (i/3) * circleGap; 

            // Draws blue circle for each group
            window.setColor(Color.BLUE);
            window.drawOval(x-radius, y-radius, 2*radius, 2*radius);

            // Labels group number above the circle
            window.setColor(Color.BLACK);
            window.drawString("Group " + (i+1), x-20, y-radius-10);

            // Write's student names names inside the circle
            List<Student> group = groups.get(i);
            
            //starting position for student names inside circcle
            int textPos = y - radius/2;
            for (Student s : group) 
            {
                window.drawString(s.getStudentName(), x -30, textPos);
                //moves down for next name
                textPos += 20; 
            }
        }
        //starting x position for student information table
        int tableX = 650;
        window.setColor(Color.BLACK);
        window.setFont(new Font("ARIEL", Font.BOLD, 14));
        window.drawString("Name       Style     Proj  Test  Char  Gen", tableX, 60);
        //starting y-position for table content
        int tableY = 85; 
        
        //Loop through each student and print their information in the table
        for (List<Student> group : groups) 
        {
            for (Student s : group) 
            {
                //Creates a string with all the student's info
                String info = s.getStudentName() + "     " + s.getLearningStyle() + "      " + s.getProjectGrade() + "    " 
                    + s.getAssessmentGrade() + "     " + s.getCharacteristics() + "     " + s.getGender();
                window.drawString(info, tableX, tableY);
                tableY += 20; 
            }
        }
    }
}
