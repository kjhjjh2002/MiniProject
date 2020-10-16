package com.kh.tm3.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;

import com.kh.tm3.controller.ApplicationManager;

public class TravelDestinationView extends JFrame {

    Object[][] rows;
    String[] header = {"코스", "체크"};

    ArrayList<Object[][]> rowsArrayList = new ArrayList<Object[][]>();

    JButton addTravelButton;
    JButton saveAndMainViewButton;

    JPanel titlePanel;
    JPanel travelPanel;
    JPanel addTravelPanel;

    JTable travelsTable;

    JTextField addTravelTextField ;

    ApplicationManager applicationManager;

    public TravelDestinationView() {
        applicationManager = ApplicationManager.getInstance();
        addTravel();
        setTravelDestinationView();

        rows = new Object[rowsArrayList.size()][rowsArrayList.size()];

        for(int i=0; i<rows.length; i++)
            rows[i] = arrayListCutter(Arrays.deepToString(rowsArrayList.get(i)));

        drawTitleView();
        drawTravelListView();
        drawAddTravelView();

        this.add(titlePanel, "North");
        this.add(travelPanel, "Center");
        this.add(addTravelPanel, "South");
        this.setVisible(true);
    }


    private void addTravel(){
        rowsArrayList.clear();

        for(int i=0; i<applicationManager.travelCourse.size(); i++){
            rowsArrayList.add(new Object[][]{{applicationManager.travelCourse.get(i),
                    applicationManager.isChecked.get(i)}});
        }
    }

    private void reStartView(){

        addTravel();

        rows = new Object[rowsArrayList.size()][rowsArrayList.size()];

        for(int i=0; i<rows.length; i++){
            rows[i] = arrayListCutter(Arrays.deepToString(rowsArrayList.get(i)));
            //System.out.println("Restart"+Arrays.toString(arrayListCutter(Arrays.deepToString(rowsArrayList.get(i)))));
        }


        drawTravelListView();

        this.add(titlePanel, "North");
        this.add(travelPanel, "Center");
        this.add(addTravelPanel, "South");
        this.setVisible(true);
    }


    private void setTravelDestinationView(){
        this.setTitle("Travel Now");
        this.setBounds(300, 200, 375, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }

    private void drawTitleView(){
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(1, 1));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 0, 0));

        JLabel titleLabel = new JLabel("여행지");
        titleLabel.setFont(titleLabel.getFont().deriveFont(36.0f));
        titlePanel.add(titleLabel);
    }



    private void drawTravelListView(){
        travelPanel = new JPanel();

        JCheckBox checkBox = new JCheckBox();
        TableCellEditor editor = new DefaultCellEditor(checkBox);
        //System.out.println("Re: "+ Arrays.deepToString(rows));
        travelsTable = new JTable(new DefaultTableModel(rows, header));
        travelsTable.getColumnModel().getColumn(1).setCellEditor(editor);

        //System.out.println("Check: "+checkBox.isSelected());



        JScrollPane scroll = new JScrollPane(travelsTable);
        scroll.setPreferredSize(new Dimension(300, 300));
        scroll.setViewportView(travelsTable);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel tableColumnModel = travelsTable.getColumnModel();

        for(int i=0; i< tableColumnModel.getColumnCount(); i++)
            tableColumnModel.getColumn(i).setCellRenderer(cellRenderer);

        travelPanel.add(scroll);
    }


    private void drawAddTravelView(){

        addTravelPanel = new JPanel();
        addTravelPanel.setLayout(new GridLayout(3,1,20,20));
        addTravelPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 50, 30));

        addTravelTextField = new JTextField(20);
        addTravelButton = new JButton("추가하기");
        saveAndMainViewButton = new JButton("저장하고 메뉴로");

        addTravelButton.setPreferredSize(new Dimension(316,46));

        addTravelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!getTextField().equals("")){
                    applicationManager.travelCourse.add(getTextField());
                    applicationManager.isChecked.add(false);

                    setTextField();

                    reStartView();
                }
            }
        });

        saveAndMainViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setIsCheck();
                saveFiles();
                ApplicationManager.getInstance().travelCourse.clear();

                new MainView();
                dispose();
            }
        });

        addTravelPanel.add(addTravelTextField);
        addTravelPanel.add(addTravelButton);
        addTravelPanel.add(saveAndMainViewButton);
    }

    private void setIsCheck() {
       // System.out.println("Table Value: "+ travelsTable.getValueAt(13, 1));
        ApplicationManager.getInstance().isChecked.clear();
        ArrayList<Boolean> check = new ArrayList<Boolean>();

        for(int i=0; i< travelsTable.getRowCount();i++){
            String value = travelsTable.getValueAt(i, 1).toString().trim();
            if(value.equals("true"))
                check.add(true);
            else
                check.add(false);

            ApplicationManager.getInstance().isChecked = check;
            System.out.println("TableValue: "+travelsTable.getValueAt(i, 1)+" , Is Check: "+ApplicationManager.getInstance().isChecked.get(i));
        }
    }

    private String getTextField(){
        return addTravelTextField.getText();
    }

    private void setTextField(){
        addTravelTextField.setText("");
    }

    private String[] arrayListCutter(String arrayList){
        String cut1 = "";
        for (int i=2; i<arrayList.length()-2;i++){
            cut1 += arrayList.charAt(i);
        }
        cut1.trim();
        String[] cut2 = cut1.split(",");

        return cut2;
    }

    private void saveFiles(){
        File courseFile = new File(ApplicationManager.getInstance().getCoursePath());
        File checkFile = new File(ApplicationManager.getInstance().getIsCheckedPath());

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(courseFile))){
            bw.write(ApplicationManager.getInstance().getTravelDestination());
            for(int i=0; i<ApplicationManager.getInstance().travelCourse.size(); i++){
                bw.write("-");
                bw.write(ApplicationManager.getInstance().travelCourse.get(i));
            }
        }catch (IOException e) {
                e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(checkFile))){
            for(int i=0; i<ApplicationManager.getInstance().isChecked.size(); i++){
                //System.out.println("Is Check: "+ ApplicationManager.getInstance().isChecked.get(i).toString());
                bw.write(ApplicationManager.getInstance().isChecked.get(i).toString());
                bw.write("-");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
