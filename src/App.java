
import farma_la_carmela.Interface.MainJFrame;
import farma_la_carmela.Model.Article;
import farma_la_carmela.Model.ArticleList;
import farma_la_carmela.Model.DataConection;
import farma_la_carmela.controller.ButtonController;
import farma_la_carmela.controller.SearchController;
import farma_la_carmela.controller.TableController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saulitron
 */
public class App {
    private static String usuario;
    private static String password;
    public static void main(String[] args){
        boolean connected=false;
        while(!connected){
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Ingrese su usuario:");
            JTextField user = new JTextField(10);
            panel.add(label);
            panel.add(user);
            String[] options = new String[]{"OK", "Cancel"};
            int option = JOptionPane.showOptionDialog(null, panel, "Usuario",
                                     JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                     null, options, options[0]);
            if(option == 0) // pressing OK button
            {
                usuario = user.getText();

                panel = new JPanel();
                label = new JLabel("Ingrese la contraseña:");
                JPasswordField pass = new JPasswordField(10);
                panel.add(label);
                panel.add(pass);
                option = JOptionPane.showOptionDialog(null, panel, "Contraseña",
                                     JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                     null, options, options[0]);
                if(option == 0) // pressing OK button
                {
                    password = new String(pass.getPassword());
                    connected=DataConection.performConnection(usuario,password);
                }else{
                    return;
                }
           
            }else{
                return;
            }

        }
        ArticleList articulos = loadArticles();
        MainJFrame frame = new MainJFrame(articulos,getPerfil(usuario));
        ButtonController bctr = new ButtonController(frame);
        SearchController sctr = new SearchController(frame);
        TableController tctr = new TableController(frame);
        frame.controller(bctr,sctr,tctr);
        
        frame.setVisible(true);

    }
    
    private static ArticleList loadArticles(){
        String query="{call allArticles()}";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
        ArticleList list= new ArticleList();
        try {
            while(rs.next()){
                list.add(new Article(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5), rs.getInt(6),rs.getDouble(7), rs.getDouble(8),rs.getDate(9),rs.getDate(10),rs.getString(11),rs.getString(12),null));                
            }
        } catch (SQLException ex) {
            Logger.getLogger("Erorr").log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    private static LinkedList getPerfil(String user){
        String query="call get_perfil_user('"+user+"');";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
                
        LinkedList<String> sb=new LinkedList();
     try {
         while(rs.next()){
             sb.add(rs.getString(1));
         }
     } catch (SQLException ex) {
         Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
     }
       return  sb;

    }
}
