import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.sql.*;

import java.time.LocalDate;
import java.util.Vector;
import com.google.gson.Gson;


class editMenu {
    editMenu(){

        Border panelborder=BorderFactory.createLineBorder(Color.orange,2);
        Font buttonFont = new Font("Calibri", Font.BOLD, 20);
        Font tableFont = new Font("Calibri", Font.PLAIN, 18);


        JLabel title=new JLabel("Menu");
        title.setFont(new Font("Futura", Font.BOLD, 50));

        JFrame frame=new JFrame("Menu");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);


        JPanel titlePanel=new JPanel();
        titlePanel.add(title);
        titlePanel.setLayout(new FlowLayout());

        titlePanel.setBorder(panelborder);
        titlePanel.setPreferredSize(new Dimension(100,100));

        JButton add1=new JButton("Add item");
        add1.setFont(buttonFont);
        add1.setPreferredSize(new Dimension(250,30));


        JButton delete=new JButton("Delete item");
        delete.setFont(buttonFont);
        delete.setPreferredSize(new Dimension(250,30));

        JButton update=new JButton("Update price");
        update.setFont(buttonFont);
        update.setPreferredSize(new Dimension(250,30));

        JButton addcat=new JButton("Add category");
        addcat.setFont(buttonFont);
        addcat.setPreferredSize(new Dimension(250,30));

        JButton getBill=new JButton("Today,s Orders");
        getBill.setFont(buttonFont);
        getBill.setPreferredSize(new Dimension(250,30));



        JPanel buttonPanel=new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        buttonPanel.setPreferredSize(new Dimension(300,100));
        buttonPanel.add(add1);
        buttonPanel.add(delete);
        buttonPanel.add(update);
        buttonPanel.add(addcat);
        buttonPanel.add(getBill);
        buttonPanel.setBorder(panelborder);

        getBill.addActionListener(
                a-> {
                    LocalDate date=LocalDate.now();
                    String filename="orders"+date+".json";
                    Path p= Paths.get(filename);

                    String url = "jdbc:mysql://localhost:3306/restro";
                    try (Connection connection = DriverManager.getConnection(url, "root", "Ashish030406")) {
                        Files.createFile(p);
                        String sql = "select * from currentorders where date(time)=curdate()";
                        try (PreparedStatement pst = connection.prepareStatement(sql)) {
                            ResultSet rs=pst.executeQuery();
                            Gson gson=new Gson();

                            while(rs.next()){
                                int tableno=rs.getInt("tableno");
                                String name=rs.getString("name");
                                int quantity=rs.getInt("quantity");
                                String status=rs.getString("status");
                                Timestamp t=rs.getTimestamp("time");

                                String result=gson.toJson(new Object[]{tableno,name,quantity,status,t});

                                Files.write(p,result.getBytes());

                            }


                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
        );


        Label cat=new Label("Categories");
        cat.setFont(new Font("Futura", Font.BOLD, 40));

        JPanel headingPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        headingPanel.add(cat);
        headingPanel.setBorder(panelborder);
        headingPanel.setPreferredSize(new Dimension(200,70));


        JPanel catButton=new JPanel(new FlowLayout(FlowLayout.LEADING,10,10));
        catButton.setBorder(panelborder);
        catButton.setPreferredSize(new Dimension(200,100));

        Vector<JButton> b=new Vector<>();



        String url = "jdbc:mysql://localhost:3306/restro";
        try (Connection con = DriverManager.getConnection(url, "root", "Ashish030406")) {
            String sql="select * from category order by id";
            try(PreparedStatement pst=con.prepareStatement(sql)){
                ResultSet rs= pst.executeQuery();

                while(rs.next()){
                   String s1=rs.getString("name");
                   JButton b1=new JButton(s1.toUpperCase());
                   b1.setFont(buttonFont);
                   b.add(b1);

                   catButton.add(b1);

                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        JButton button1=new JButton("Starters");
        button1.setFont(buttonFont);
        JButton button2=new JButton("Main Course");
        button2.setFont(buttonFont);


        ButtonGroup buttonGroup=new ButtonGroup();
        buttonGroup.add(button1);
        buttonGroup.add(button2);





        JPanel dataPanel=new JPanel(new BorderLayout());
        dataPanel.setBorder(panelborder);
        dataPanel.setPreferredSize(new Dimension(100,700));
//        dataPanel.add(gridPanel1);
//        dataPanel.add(gridPanel2);
//        dataPanel.add(gridPanel3);
//        dataPanel.add(gridPanel4);




        String[] columnNames={"Id","Name","Price"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        table.setFont(tableFont);
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 18));
        table.getTableHeader().setBackground(Color.orange);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(224, 224, 224));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600,400));
        dataPanel.add(scrollPane,BorderLayout.WEST);

        JPanel container=new JPanel(new BorderLayout());
        container.add(headingPanel,BorderLayout.NORTH);
        container.add(catButton,BorderLayout.NORTH);
        container.add(dataPanel,BorderLayout.CENTER);





        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(buttonPanel,BorderLayout.WEST);
        frame.add(container,BorderLayout.CENTER);

        try (Connection con = DriverManager.getConnection(url, "root", "Ashish030406")) {
            String sql="select * from menu where category=? order by id";
            try(PreparedStatement pst=con.prepareStatement(sql)){
                String s1=b.get(0).getText().toLowerCase();
                pst.setString(1,s1);
                ResultSet rs= pst.executeQuery();

                while (rs.next()){
                    double id=rs.getDouble("id");
                    String name=rs.getString("name");
                    double price=rs.getDouble("price");


                    tableModel.addRow(new Object[]{(int)id,name,price});
                }

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }




        add1.addActionListener(
                a->{

new addItem();
frame.dispose();


                }

        );

        addcat.addActionListener(
                a->{
                   new AddCategory();
                   frame.dispose();
                }
        );

        for (JButton ditto:b){
            ditto.addActionListener(
                    a->{
                       String s1= ditto.getText().toLowerCase();
                       tableModel.setRowCount(0);

                        try (Connection con = DriverManager.getConnection(url, "root", "Ashish030406")) {
                            String sql="select * from menu where category=? order by id";
                            try(PreparedStatement pst=con.prepareStatement(sql)){
                                pst.setString(1,s1);

                                ResultSet rs= pst.executeQuery();

                                while (rs.next()){
                                 double id=rs.getDouble("id");
                                 String name=rs.getString("name");
                                 double price=rs.getDouble("price");


                                    tableModel.addRow(new Object[]{(int)id,name,price});
                                }

                            }
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }

                    }
            );
        }
    }

    public static void main(String[] args) {
        new editMenu();
    }
}
