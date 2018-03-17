import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class application extends JFrame{
    private JTextArea historyTextArea;
    private JTextField formulaInput;
    private JButton eventButton;
    private JPanel mainPanel;
    private JScrollPane scrollHistoryTextArea;
    private JList<String> functionList;

    JMenuItem item1 = new JMenuItem("Reset");
    JMenuItem item2 = new JMenuItem("Exit");
    List<String> history = new ArrayList();
    int counter=0;
    String lastResult="";

    public application() {
        final JFrame frame = new JFrame("SciCalcu");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,750);
        JMenuBar menuBar = new JMenuBar();

        setJMenuBar(menuBar);
        JMenu option = new JMenu("Opcje");
        menuBar.add(option);
        frame.setJMenuBar(menuBar);
        item2.setMnemonic(KeyEvent.VK_E);
        option.add(item1);
        option.add(item2);

        frame.setVisible(true);
        item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                historyTextArea.setText(null);
            }
        });

        item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        formulaInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                history.add(formulaInput.getText());
                counter = 0;
                historyTextArea.append(new message().message_1(formulaInput.getText()));
                formulaInput.setText(null);

            }
        });
        formulaInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    if (!history.isEmpty()){
                        formulaInput.setText(history.get(history.size()-counter-1));

                        if(counter < history.size()-1){
                            counter++;
                        }
                    }
                }
            }
        });
        functionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                if (functionList.getSelectedIndex() == 0) {
                    formulaInput.setText("sin()");
                    formulaInput.requestFocus();
                    formulaInput.setCaretPosition(formulaInput.getText().length()-1);
                }else if (functionList.getSelectedIndex() == 1) {
                    formulaInput.setText("cos()");
                    formulaInput.requestFocus();
                    formulaInput.setCaretPosition(formulaInput.getText().length()-1);
                }else if (functionList.getSelectedIndex() == 2) {
                    formulaInput.setText("tg()");
                    formulaInput.requestFocus();
                    formulaInput.setCaretPosition(formulaInput.getText().length()-1);
                }else if (functionList.getSelectedIndex() == 3) {
                    formulaInput.setText("mod( ,)");
                    formulaInput.requestFocus();
                    formulaInput.setCaretPosition(formulaInput.getText().length() - 2);
                }else if (functionList.getSelectedIndex() == 4) {
                    formulaInput.setText("log( ,)");
                    formulaInput.requestFocus();
                    formulaInput.setCaretPosition(formulaInput.getText().length() - 2);
                }else if (functionList.getSelectedIndex() == 5) {
                    formulaInput.setText("C( ,)");
                    formulaInput.requestFocus();
                    formulaInput.setCaretPosition(formulaInput.getText().length() - 2);
                }else if (functionList.getSelectedIndex() == 6) {
                    formulaInput.setText("pi");
                    formulaInput.requestFocus();
                    formulaInput.setCaretPosition(formulaInput.getText().length());
                }else if (functionList.getSelectedIndex() == 7) {
                    formulaInput.setText("e");
                    formulaInput.requestFocus();
                    formulaInput.setCaretPosition(formulaInput.getText().length());
                }else if (functionList.getSelectedIndex() == 8) {
                    formulaInput.setText("[PN]");
                    formulaInput.requestFocus();
                    formulaInput.setCaretPosition(formulaInput.getText().length());
                }else if (functionList.getSelectedIndex() == 9) {
                    if (!history.isEmpty()) {
                        historyTextArea.append(new message().message_1(history.get(history.size() - counter - 1)));
                    }
                }
            }
        });
        eventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                history.add(formulaInput.getText());
                counter = 0;
                historyTextArea.append(new message().message_1(formulaInput.getText()));
                formulaInput.setText(null);
            }
        });
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
