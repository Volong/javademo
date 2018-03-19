package lambda;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ListenerTest {

    public static void main(String[] args) {
        
        JButton jButton = new JButton();
        jButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("anon button");
            }
        });
        
        jButton.addActionListener(l -> System.out.println("lambda buttion"));
        
        
        JFrame jFrame = new JFrame("Lambda test");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(jButton, BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setVisible(true);
        
        
    }
}    
