/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopgame;

import com.sun.javafx.tk.Toolkit;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author marcin
 */
public class ShopGame extends JFrame{
    
    ShopGame(){
        
        
        initsComponent();
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ShopGame().setVisible(true);
    }
    
    private void initsComponent(){
        
        this.setTitle("Try survive to next week");
        this.setBounds(200, 200, 800, 500);
        
        
        
        //Nastepnie okreslic ich Layout
        panel.setLayout(layout);
        
        
        
        //1 Element Iland
        allStuff();
        this.listIland.setBorder(BorderFactory.createTitledBorder("Iland"));
        this.listIland.setFixedCellHeight(25);
        this.listIland.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(scrollListIland);
        
        
        //2 Element
        panel.add(this.buttonToWorkshop);
        this.buttonToWorkshop.addActionListener(new toWorkshop());
        
        //3 Element Workshop
        this.listWorkshop.setBorder(BorderFactory.createTitledBorder("Workshop"));
        this.listWorkshop.setFixedCellHeight(25);
        panel.add(scrollListWorkshop);
        
        //4 Element
        panel.add(this.buttonToUser);
        this.buttonToUser.addActionListener(new toUser());
        
        //5 Element User
        this.listUser.setBorder(BorderFactory.createTitledBorder("User equipment"));
        this.listUser.setFixedCellHeight(25);
        this.listUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.listUser.setEnabled(false);
        this.listUser.setForeground(new Color(11,115,56));
        panel.add(scrollListUser);
        
        //Bottom
        panel.add(fieldDay).setEnabled(false);
        panel.add(fieldWeek).setEnabled(false);
        //To Craft
        this.listToCraft.setBorder(BorderFactory.createTitledBorder("To craft"));
        this.listToCraft.setFixedCellHeight(25);
        this.listToCraft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        creatBase();
        this.listToCraft.addListSelectionListener(new toCraft());
        panel.add(scrollListToCraft);
        //UNeed Items
        this.listUNeed.setBorder(BorderFactory.createTitledBorder("You need"));
        this.listUNeed.setFixedCellHeight(25);
        this.listUNeed.setEnabled(false);
        this.listUNeed.setForeground(new Color(11,115,56));
        panel.add(scrollListUNeed);
        
        this.fieldInfo.setForeground(new Color(11,115,56));
        uNeedToSurvive(0);
        panel.add(fieldInfo).setEnabled(false);
        
        
        
        //Dodac do szybki
        getContentPane().add(panel);
        
        
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    //Elements
    private JPanel panel = new JPanel();
    private final GridLayout layout = new GridLayout(2,6);
    private JTextField fieldDay = new JTextField("Day: "+ day);
    private JTextField fieldWeek = new JTextField("Week: " + week);
    private JTextArea fieldInfo = new JTextArea("");
    //Iland
    private DefaultListModel tmpIland = new DefaultListModel();
    private JList listIland = new JList(tmpIland);
    private JScrollPane scrollListIland = new JScrollPane(listIland);
    //Workshop
    private DefaultListModel tmpWorkshop = new DefaultListModel();
    private JList listWorkshop = new JList(tmpWorkshop);
    private JScrollPane scrollListWorkshop = new JScrollPane(listWorkshop);
    //UserEQ
    private DefaultListModel tmpUser = new DefaultListModel();
    private JList listUser = new JList(tmpUser);
    private JScrollPane scrollListUser = new JScrollPane(listUser);
    //ToCraft
    private DefaultListModel tmpToCraft = new DefaultListModel();
    private JList listToCraft = new JList(tmpToCraft);
    private JScrollPane scrollListToCraft = new JScrollPane(listToCraft);
    //UNeed
    private DefaultListModel tmpUNeed = new DefaultListModel();
    private JList listUNeed = new JList(tmpUNeed);
    private JScrollPane scrollListUNeed = new JScrollPane(listUNeed);
    //Buttony
    JButton buttonToWorkshop = new JButton("->");
    JButton buttonToUser = new JButton("->");
    //Day and week
    private static int day = 1;
    private static int week = 1;
    
    
    
    //If next week , usuwa wszystkie lelementy z Iland i tworzy je na nowo fukcja allStuff()
    private void ifNextWeek(){
        if (week == 0){
            System.exit(0);
        }
        day++;
        if (day == 8){
            week += 1;
            day = 1;
            fieldWeek.setText("Week: "+ week);
            tmpIland.removeAllElements();
            allStuff();
            uNeedToSurvive(week-1);
        }
        fieldDay.setText("Day: "+ day);
        
    }
    //Rzeczy ktore zostana usuniete w przyszlym tygodniu
    private void uNeedToSurvive(int i){
        // i is a numer of Week
        int tmp;
        switch (i){
            case 0:
                // Wypisuje tylko
                fieldInfo.setText("You need in next Week: \n woda \n "
            + "\n Next next Week: \n woda \n chleb \n galezie \n plachta \n"
            + "\n Next next next Week: \n Nozyk \n chleb");
                break;
            case 1:
                // Sprawdza przedmioty i je usuwa
                tmp = 0;
                if (tmpUser.removeElement("woda") == true)
                    tmp++;
 
                
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                if (tmp == 1){
                       
                fieldInfo.setText("You need in next Week: \n woda \n chleb \n galezie \n plachta \n"    //2 Week
            + "\n Next next Week: \n Nozyk \n chleb \n "                                                //3 Week
            + "\n Next next next Week: \n kokos \n woda \n chleb \n");                                  //4 Week
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 2:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("woda") == true)
                    tmp++;
                if (tmpUser.removeElement("chleb") == true)
                    tmp++;
                if (tmpUser.removeElement("galezie") == true)
                    tmp++;
                if (tmpUser.removeElement("plachta") == true)
                    tmp++;


                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 4){
                       
                fieldInfo.setText("You need in next Week: \n Nozyk \n chleb \n "   
            + "\n Next next Week: \n kokos \n woda \n chleb \n"                                              
            + "\n Next next next Week: \n Kurtka \n Buty \n Spodnie \n");                                  
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 3:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("Nozyk") == true)
                    tmp++;
                if (tmpUser.removeElement("chleb") == true)
                    tmp++;
           
                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 2){
                       
                fieldInfo.setText("You need in next Week: \n kokos \n woda \n chleb \n "    
            + "\n Next next Week: \n Kurtka \n Buty \n Spodnie\n "                                                
            + "\n Next next next Week: \n woda ");                                  
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 4:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("woda") == true)
                    tmp++;
                if (tmpUser.removeElement("chleb") == true)
                    tmp++;
                if (tmpUser.removeElement("kokos") == true)
                    tmp++;


                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 3){
                       
                fieldInfo.setText("You need in next Week: \n Kurtka \n Buty \n Spodnie \n "    
            + "\n Next next Week: \n woda \n"                                                
            + "\n Next next next Week: \n Radio \n kokos");                                  
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 5:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("Kurtka") == true)
                    tmp++;
                if (tmpUser.removeElement("Buty") == true)
                    tmp++;
                if (tmpUser.removeElement("Spodnie") == true)
                    tmp++;


                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 3){
                       
                fieldInfo.setText("You need in next Week: \n woda \n"    
            + "\n Next next Week: \n Radio \n kokos \n"                                                
            + "\n Next next next Week: \n Luk \n Namiot");                                  
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 6:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("woda") == true)
                    tmp++;

                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 1){
                       
                fieldInfo.setText("You need in next Week: \n Radio \n kokos \n"    
            + "\n Next next Week: \n Luk \n Namiot \n"                                                
            + "\n Next next next Week: \n Ognisko \n mieso");                                  
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 7:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("Radio") == true)
                    tmp++;
                if (tmpUser.removeElement("kokos") == true)
                    tmp++;


                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 2){
                       
                fieldInfo.setText("You need in next Week: \n Luk \n Namiot \n"    
            + "\n Next next Week: \n Ognisko \n mieso \n"                                                
            + "\n Next next next Week: \n Szycie \n Czapka");                                  
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 8:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("Luk") == true)
                    tmp++;
                if (tmpUser.removeElement("Namiot") == true)
                    tmp++;


                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 2){
                       
                fieldInfo.setText("You need in next Week: \n Ognisko \n mieso \n"    
            + "\n Next next Week: \n Szycie \n Czapka \n"                                                
            + "\n Next next next Week: \n woda ");                                  
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 9:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("Ognisko") == true)
                    tmp++;
                if (tmpUser.removeElement("mieso") == true)
                    tmp++;


                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 2){
                       
                fieldInfo.setText("You need in next Week: \n Szycie \n Czapka \n"    
            + "\n Next next Week: \n woda \n"                                                
            + "\n Next next next Week: \n Lodka \n 4x drewno \n woda ");                                  
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 10:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("Szycie") == true)
                    tmp++;
                if (tmpUser.removeElement("Czapka") == true)
                    tmp++;


                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 2){
                       
                fieldInfo.setText("You need in next Week: \n woda \n"    
            + "\n Next next Week:  \n Lodka \n 4x drewno \n woda \n"                                                
            + "\n Next next next Week:\n  Kilof \n Lopata \n woda \n");                                  
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 11:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("woda") == true)
                    tmp++;

                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 1){
                       
                fieldInfo.setText("You need in next Week: \n Lodka \n 4x drewno \n woda \n"    
            + "\n Next next Week: \n  Kilof \n Lopata \n woda \n "                                                
            + "\n Next next next Week: \n Filtr wody \n Ucieknij \n");                                  
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 12:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("Lodka") == true)
                    tmp++;
                if (tmpUser.removeElement("drewno") == true)
                    tmp++;
                if (tmpUser.removeElement("drewno") == true)
                    tmp++;
                if (tmpUser.removeElement("drewno") == true)
                    tmp++;
                if (tmpUser.removeElement("drewno") == true)
                    tmp++;
                if (tmpUser.removeElement("woda") == true)
                    tmp++;

                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 6){
                       
                fieldInfo.setText("You need in next Week: \n  Kilof \n Lopata \n woda \n "    
            + "\n Next next Week: \n Filtr wody \n Ucieknij \n"                                                
            + "");                                  
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 13:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("Kilof") == true)
                    tmp++;
                if (tmpUser.removeElement("Lopata") == true)
                    tmp++;
                if (tmpUser.removeElement("woda") == true)
                    tmp++;
                

                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 3){
                       
                fieldInfo.setText("You need in next Week: \n Filtr wody \n Ucieknij \n"    
            + ""                                                
            + "");                                  
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
            case 14:
                //Jak sa wszystkie to ustawia sie na nowy tydzien jak nie to umierasz
                tmp = 0;
                if (tmpUser.removeElement("Filtr wody") == true)
                    tmp++;
                if (tmpUser.removeElement("Ucieknij") == true)
                    tmp++;
                
                

                //Sprawdza i od razu usuwa element, nastepnie ustawia tekst na nowy tydzien
                if (tmp == 2){
                       
                fieldInfo.setText("Wygrales, brawo");
                week = 0;
                
                }else{
                    //Wypisuje ze umarles i zmienia week na 0 bo w fukcji ifNextWeek() jeżeli Week jest ustwiony na 0 to zamyka program
                    fieldInfo.setText("\n You died");
                    week = 0;
                }
                break;
                
        }

    }
    //Rzeczy do crafcenia
    private Boolean canICraft (){
        
        Integer [] tmp = new Integer[listWorkshop.getSelectedIndices().length];
   
        //For ktory pobiera jakie przedmioty sa zaznaczone 
         for (int i = 0; i < tmp.length ; i++)
         {
            tmp[i] = listWorkshop.getSelectedIndex();
            listWorkshop.removeSelectionInterval(listWorkshop.getSelectedIndex(), listWorkshop.getSelectedIndex());
         }

            return checkCraft(tmp);
    }
     private Boolean checkCraft(Integer tmp[]){
         
         int j = tmp.length;
         //Sprawdza w bazie czy jest mozliwe craftowanie dla 4 elementów sprawdzanych
             int match = 0;
             int row = 0;
             for (int elements = 0; elements < anArray.length/5 ; elements++){
             for (int i = 0; i < j ; i++)
            {
                for (int h = 0; h < j ; h++)
                { 
                    listWorkshop.setSelectedIndex(tmp[i]);
                    if (anArray[row+h].equals(listWorkshop.getSelectedValue()) == true){
                        match+=1;
                    }else{
                        
                    }
                     
                }
             
             }
             
             if (match == j){
                 for (int i = 1 ; i <= j ; i++){
                     tmpWorkshop.removeElementAt(tmp[j-i]);
                     //System.out.println(listWorkshop.getSelectedValuesList());
                 }
                 
                 tmpUser.addElement(anArray[row+4]);
                 
                 System.out.println("item crafted");
                 return true;
            }else{
               row += 5; 
               match = 0;
             }
            
         }
         
         
         // j jest to liczba elementow jakie trzeba sprawdzic od 0 do 4 to jest 1 item ktory mozna craftowac 
         if (j != tmp.length){
             return false; //rozna ilosc zaznaczonych wzgledem sprawdzanych
         }else{
         
         
         
         
         return false;
     }
         
     
     }
         
     
         
     //Koniec Bazy
         // Implementacja Bazy
         private void creatBase (){
             int max_size = anArray.length;
             
             for (int i = 4 ; i < max_size ; i+= 5)
                 tmpToCraft.addElement(anArray[i]);   
         }
         private int findInBase(String name){
             int max_size = anArray.length;
             
             for (int i = 4 ; i < max_size ; i+= 5){
                 if (name.equals(anArray[i]) == true){
                     return i;
                 }
            }
            return 0;
         }
         private void showBaseItems(int tmp){
             for (int i = 1 ; i < 5 ; i++){
                 if (anArray[tmp-i].equals("null") == false)
                 tmpUNeed.addElement(anArray[tmp-i]);
             }
                 
         }
         
    //Actions Listeners    
   private class toWorkshop implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String selected = new String("");
            if (!listIland.isSelectionEmpty()){
                selected =(String)listIland.getSelectedValue();
                tmpIland.removeElementAt(listIland.getSelectedIndex());
                tmpWorkshop.addElement(selected);
                
                
                ifNextWeek();
            }else{
               System.out.print("Pusto"); 
            }
           
        }
    
    
    }
   private class toUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             String selected = new String("");
            if (!listWorkshop.isSelectionEmpty()){
                if((listWorkshop.getSelectedIndices()).length > 1)
                {   
                    if (canICraft() == true){
                        
                        ifNextWeek();
                    }
                    else{
                        System.out.println("You cannot craft");
                    }
                }
                else{
                    selected =(String)listWorkshop.getSelectedValue();
                    tmpWorkshop.removeElementAt(listWorkshop.getSelectedIndex());
                    tmpUser.addElement(selected);
                
                    
                    ifNextWeek();
                     
                }
            
            }
       
        }
   }
   private class toCraft implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == true){
                tmpUNeed.removeAllElements();
                showBaseItems(findInBase(""+listToCraft.getSelectedValue()));
            System.out.println("" + listToCraft.getSelectedValue());
            
            
            }
        }
        @Override
        public String toString(){
            return "" + listToCraft.getSelectedValue();
        }
       
   }
    
    
    //methods
    private void allStuff (){
       
        this.tmpIland.addElement("woda");
        this.tmpIland.addElement("antena");
        this.tmpIland.addElement("butelka");
        this.tmpIland.addElement("chleb");
        this.tmpIland.addElement("dlugi kij");
        this.tmpIland.addElement("drewno");
        this.tmpIland.addElement("folia");
        this.tmpIland.addElement("futro");
        this.tmpIland.addElement("galezie");
        this.tmpIland.addElement("glosnik");
        this.tmpIland.addElement("gwozdek");
        this.tmpIland.addElement("igla");
        this.tmpIland.addElement("kable");
        this.tmpIland.addElement("karton");
        this.tmpIland.addElement("kawalek metalu");
        this.tmpIland.addElement("kij");
        this.tmpIland.addElement("kokos");
        this.tmpIland.addElement("kostka drewna");
        this.tmpIland.addElement("linka");
        this.tmpIland.addElement("mieso");
        this.tmpIland.addElement("nitka");
        this.tmpIland.addElement("ostra blacha");
        this.tmpIland.addElement("patyk");
        this.tmpIland.addElement("piasek");
        this.tmpIland.addElement("plachta");
        this.tmpIland.addElement("pokretlo");
        this.tmpIland.addElement("siano");
        this.tmpIland.addElement("skora");
        this.tmpIland.addElement("szklo");
        this.tmpIland.addElement("wegiel");
        
        
        
    }
    

// Baza przediotow do Craftu
     
         
         final String [] anArray ={
                                    
                                    "skora", "linka","null","null","Buty",
                                    "linka", "skora","karton","null","Czapka",
                                    "wegiel", "piasek","butelka","null","Filtr wody",
                                    "kij", "linka","kawalek metalu","null","Kilof",
                                    "futro", "nitka","null","null","Kurtka",
                                    "drewno", "patyk","plachta","linka","Lodka",
                                    "dlugi kij", "ostra blacha","null","null","Lopata",
                                    "patyk", "linka","gwozdek","null","Luk",
                                    "plachta", "folia","galezie","linka","Namiot",
                                    "ostra blacha", "linka","kostka drewna","null","Nozyk",
                                    "galezie", "szklo","patyk","siano","Ognisko",
                                    "pokretlo", "antena","glosnik","kable","Radio", 
                                    "skora", "igla","nitka","null","Spodnie",
                                    "woda", "igla","nitka","null","Szycie",
                                    "woda", "kokos","null","null","Ucieknij",
                                    
                 
         };
}
/**
 * Game system 
 * 1 Week [(2/7),(2/7)] - woda
 * 2 Week [(8/7),(10/14)]- woda , chleb , galezie , plachta
 * 3 Week [(6/7),(16/21)]- Nozyk -> ostra blacha + linka + kostka drewna , chleb
 * 4 Week [(6/7),(22/28)]- kokos , woda , chleb
 * 5 Week [(10/7),(32/35)]- Kurtka -> futro + nitka , Buty -> skora + linka , Spodnie -> skora + igla + nitka
 * 6 Week [(2/7),(34/42)]- woda
 * 7 Week [(7/7),(41/49)]- Radio -> pokretlo + antena + glosnik + kable , kokos
 * 8 Week [(9/7),(50/56)]- Luk -> paryk + linka + gwozdek , Namiot -> plachta + folia + galezie + linka
 * 9 Week [(7/7),(57/63)]- Ognisko -> galezie + szkolo + patyk + siano , mieso
 * 10 Week [(8/7),(65/70)]- Szycie -> woda + igla + nitka , Czapka -> linka + skora + karton
 * 11 Week [(2/7),(67/77)] - woda
 * 12 Week [(15/7),(82/84)]- Lodka -> drewno + patyk + plachta + linka , 4x drewno , woda 
 * 13 Week [(9/7),(91/91)]- Kilof -> kij + linka + kawalek metalu , Lopata -> dlugi kij + ostra blacha, woda 
 * 14 Week [(7/7),(98/98)]- Ucieknij -> woda + kokos , Filtr wody -> wegiel + piasek + butelka
 * 
 * 
 * 1 Week - woda
 * 2 Week - woda , chleb , galezie , plachta
 * 3 Week - Nozyk , chleb
 * 4 Week - kokos , woda , chleb
 * 5 Week - Kurtka , Buty , Spodnie
 * 6 Week - woda
 * 7 Week - Radio , kokos
 * 8 Week - Luk , Namiot
 * 9 Week - Ognisko , mieso
 * 10 Week - Szycie , Czapka
 * 11 Week - woda
 * 12 Week - Lodka , drewno , drewno , drewno , drewno , woda
 * 13 Week - Kilof , Lopata , woda
 * 14 Week - Ucieknij , Filtr wody
 */

/**
 * How to complete the game
 * 
 * 1 Week - woda , linka , drewno , galezie , nitka , skora
 * 2 Week - woda , chleb , plachta
 * 3 Week - ostra blacha , linka , kostka drewna , chleb , drewno
 * 4 Week - woda , kokos , chleb , linka
 * 5 Week - futro , skora , igla , nitka
 * 6 Week - woda , drewno , linka , patyk , gwozdek , folia
 * 7 Week - kokos , kable , glosnik , antena , pokretlo 
 * 8 Week - plachta , galezie , woda , drewno , linka
 * 9 Week - mieso , siano , patyk , szklo , galezie
 * 10 Week - igla , nitka , karton , skora , linka
 * 11 Week - woda , drewno , patyk , plachta , linka , kij
 * 12 Week - woda
 * 13 Week - kawalek metalu , dlugi kij , ostra blacha , woda
 * 14 Week - woda , kokos , butelka, wegiel , piasek
 * 
 * 
 */

