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
    private JList<Calc> functionList;
    JMenuItem item1 = new JMenuItem("Reset");
    JMenuItem item2 = new JMenuItem("Exit");
    List<String> history = new ArrayList();
    int counter=0;
    String lastResult="";

    public application() {
        Calc sin = new Calc("sinus", "sin()");
        Calc cos = new Calc("cosinus", "cos()");
        Calc tg = new Calc("tangrns", "tg()");
        Calc mod = new Calc("modulo", "mod( ,)");
        Calc C = new Calc("Binomial coefficient", "C( ,)");
        Calc pi = new Calc("PI", "pi");
        Calc e = new Calc("Euler's number", "e");
        Calc PN = new Calc("Plastic constant", "[PN]");
        Calc last = new Calc("last result", "last");

        DefaultListModel<Calc> listModel = new DefaultListModel<>();
        listModel.addElement(sin);
        listModel.addElement(cos);
        listModel.addElement(tg);
        listModel.addElement(mod);
        listModel.addElement(C);
        listModel.addElement(pi);
        listModel.addElement(e);
        listModel.addElement(PN);
        listModel.addElement(last);

        functionList.setModel(listModel);

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
                if (functionList.getSelectedValue().getName() != null)
                {
                    if(functionList.getSelectedValue().getName() == "last result") {
                        formulaInput.setText(new message().message_2(history.get(history.size() - counter - 1)));
                    }else {
                        formulaInput.setText(functionList.getSelectedValue().getCode());
                        formulaInput.requestFocus();
                        formulaInput.setCaretPosition(formulaInput.getText().length() - 1);
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
