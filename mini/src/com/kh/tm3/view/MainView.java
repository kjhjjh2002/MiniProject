package com.kh.tm3.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.kh.tm3.controller.ApplicationManager;
import com.kh.tm3.controller.DataParse;

public class MainView extends JFrame {

    String[] travels;

    Boolean[] isCheck;


    public MainView() {
        super("MainView");

        this.setTitle("Travel Now");
        this.setBounds(300, 200, 375, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        setTravelsList();
        setIsCheck();

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(2, 1));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 0, 0));

        JLabel travelLabel = new JLabel("나의 여행");
        travelLabel.setHorizontalAlignment(JLabel.LEFT);
        travelLabel.setFont(travelLabel.getFont().deriveFont(36.0f));

        JLabel startText = new JLabel(ApplicationManager.getInstance().getTravelDestination());
        startText.setFont(travelLabel.getFont().deriveFont(18.0f));

        titlePanel.add(travelLabel);
        titlePanel.add(startText);
        this.add(titlePanel, "North");


        JPanel travelPanel = new JPanel();

        JPanel listPanel = new JPanel();

        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));



        //System.out.println("isCheckSize: "+isCheck.length+", travelsSize: "+travels.length);

        for(int i = 0; i<travels.length; i++){
            JLabel newLabel = new JLabel(travels[i]);
            newLabel.setFont(newLabel.getFont().deriveFont(16.0f));

            if(isCheck[i])
                newLabel.setForeground(Color.BLUE);
            else
                newLabel.setForeground(Color.BLACK);

            listPanel.add(newLabel);
        }

        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setPreferredSize(new Dimension(300, 280));

        travelPanel.add(scroll);

        this.add(travelPanel, "Center");

        JPanel selectButtonPanel = new JPanel();
        selectButtonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        selectButtonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 25, 30));

        JButton travelButton = new JButton("여행지");
        travelButton.setPreferredSize(new Dimension(152, 40));
        JButton budgetButton = new JButton("예산");
        JButton materialButton = new JButton("준비물");
        JButton spendButton = new JButton("지출");
        JButton memoButton = new JButton("메모장");
        JButton profileButton = new JButton("프로필");

        selectButtonPanel.add(travelButton);
        selectButtonPanel.add(budgetButton);
        selectButtonPanel.add(materialButton);
        selectButtonPanel.add(spendButton);
        selectButtonPanel.add(memoButton);
        selectButtonPanel.add(profileButton);

        travelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TravelDestinationView();
                dispose();
            }
        });

        this.add(selectButtonPanel, "South");


        this.setVisible(true);
    }

    private void setTravelsList() {
    	DataParse dataParsing = new DataParse();
        String[] data = dataParsing.parsingData(dataParsing.readFile("course"));

        travels = new String[data.length-1];
        ApplicationManager.getInstance().setTravelDestination(data[0]);

        for(int i=0; i<data.length;i++){
            if(i<data.length-1){
                travels[i] = data[i+1];
                ApplicationManager.getInstance().travelCourse.add(travels[i]);
                travels[i] = (i+1)+" - "+travels[i];
                //System.out.println("Set: "+ApplicationManager.getInstance().travelCourse.get(i));
            }

        }


    }

    private void setIsCheck(){
        DataParse dataParsing = new DataParse();
        String[] data = dataParsing.parsingData(dataParsing.readFile("check"));

        isCheck = new Boolean[data.length];

        for(int i=0; i<data.length;i++){
            if(data[i].equals("true"))
                isCheck[i] = true;
            else
                isCheck[i] = false;

            //System.out.println("isCheck: "+isCheck[i]);
            ApplicationManager.getInstance().isChecked.add(isCheck[i]);
        }


    }
}
