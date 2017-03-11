/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma_la_carmela.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saulitron
 */
public class ArticleList extends LinkedList<Article>{
    private String query;
    
    public boolean containsArticle(String nombre, String pres, Date fex){
        boolean found = false;
        query="{call getid_articulo('" + nombre+ "','"+pres+"','"+fex+"')}";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
        try {
            if(rs.next()&&this.getArticle(rs.getInt(1))!=null){
                found= true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return found;
    }
    
    public ArticleList getArticles(String name){
        ArticleList subList = new ArticleList();
        query="{call getArticuloID('%" + name + "%')}";
        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
        try{
            while(rs.next()){
                Article tmp = this.getArticle(rs.getInt(1));
                if(!tmp.equals(null)){
                    subList.add(tmp);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return subList;
    }
    
    public Article getArticle(int idA){
        Iterator<Article> itr = this.iterator();
        while(itr.hasNext()){
            Article tmp = itr.next();
            if(tmp.getId()==idA){
                return tmp;
            }
        }
        return null;
    }
    
    
    
}
    