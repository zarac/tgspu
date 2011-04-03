package laboration17;
import java.awt.*;
import javax.swing.*;
 
public class NamnYrke extends JPanel 
{
    private JLabel namnLabel;
    private JLabel yrkeLabel;
    private JTextField namnTextField;
    private JTextField yrkeTextField;
 
    public NamnYrke() 
    { 
        namnLabel = new JLabel("Namn");
        yrkeLabel = new JLabel("Yrke");
        namnTextField = new JTextField();
        yrkeTextField = new JTextField();

        setLayout(new BorderLayout());
        JPanel labelPanel = getLabelPanel();
        JPanel inputPanel = getInputPanel();
        labelPanel.setPreferredSize(new Dimension(50, 0));
        add(labelPanel, BorderLayout.WEST);
        add(inputPanel, BorderLayout.CENTER);
    } 
 
    public JPanel getLabelPanel()
    {
        JPanel panel = new JPanel(new GridLayout(2,1));
        Font dialogB12 = new Font("Dialog", Font.BOLD, 12);
        namnLabel.setFont(dialogB12);
        yrkeLabel.setFont(dialogB12);
        panel.add(namnLabel);
        panel.add(yrkeLabel);
        return panel;
    }

    public JPanel getInputPanel()
    {
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(namnTextField);
        panel.add(yrkeTextField);
        return panel;
    }
 
    public String getNamn() 
    { 
        return namnTextField.getText();
    } 
 
    public String getYrke() 
    { 
        return yrkeTextField.getText();
    } 


    public static void main(String[] argv)
    {
        new NamnYrke();
    }
} 
