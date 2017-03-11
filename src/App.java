
import farma_la_carmela.Interface.MainJFrame;
import farma_la_carmela.Model.Article;
import farma_la_carmela.Model.ArticleList;
import farma_la_carmela.Model.DataConection;
import farma_la_carmela.Model.Medicina;
import farma_la_carmela.Model.Producto;
import farma_la_carmela.controller.ButtonController;
import farma_la_carmela.controller.SearchController;
import farma_la_carmela.controller.TableController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static void main(String[] args){
        DataConection.performConnection();
        MainJFrame frame = new MainJFrame();
        ArticleList articulos = loadArticles();
        ButtonController bctr = new ButtonController(frame,articulos);
        SearchController sctr = new SearchController(frame,articulos);
        TableController tctr = new TableController(frame,articulos);
        frame.controller(bctr,sctr,tctr);
        
        frame.setVisible(true);
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
}
