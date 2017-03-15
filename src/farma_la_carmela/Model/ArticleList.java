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
        if(getArticle(nombre,pres,fex)!=null){
            found=true;
        }
        return found;
    }
    
    public Article getArticle(String nombre, String pres, Date fex){
        //Article found = null;
        Iterator<Article> itr = this.iterator();
        while(itr.hasNext()){
            Article tmp = itr.next();
            if(tmp.getNombre().contains(nombre.toUpperCase())&&tmp.getPresentacion().contains(pres.toUpperCase())&&tmp.getFecha_caducidad().equals(fex)){
                return tmp;
            }
        }
        return null;
    }    
        
//        query="{call getid_articulo('" + nombre+ "','"+pres+"','"+fex+"')}";
//        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
//        try {
//            if(rs.next()){
//                found = this.getArticle(rs.getInt(1));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ArticleList.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return found;
    
    
    public ArticleList getArticles(String name){
        ArticleList subList = new ArticleList();
        Iterator<Article> itr = this.iterator();
        while(itr.hasNext()){
            Article tmp = itr.next();
            if(tmp.getNombre().contains(name.toUpperCase())){
                subList.add(tmp);
            }
        }
        return subList;
    }
    
    public void modArticle(int idA, String n, String p,String cb,int s,int sm,double c, double pvp,Date exp,String ca,String r,String[] comp){
        Iterator<Article> itr = this.iterator();
        boolean modDone = false;
        while(!modDone&&itr.hasNext()){
            Article tmp = itr.next();
            if(tmp.getId()==idA){
                this.remove(tmp);
                tmp.updateArticle(n,p,cb,s,sm,c,pvp,exp,ca,r,comp);
                this.add(tmp);
                modDone=true;
            }
        }
    }
    
    public void buyArticle(int idA, int idC, Date f,int cnt){
        Iterator<Article> itr = this.iterator();
        boolean buyDone = false;
        while(!buyDone&&itr.hasNext()){
            Article tmp = itr.next();
            if(tmp.getId()==idA){
                this.remove(tmp);
                tmp.buy(idC, f, cnt);
                this.add(tmp);
                buyDone=true;
            }
        }
    }
        
        
//        query="{call getArticuloID('%" + name + "%')}";
//        ResultSet rs = DataConection.ejecutarProcedureSelect(query);
//        try{
//            while(rs.next()){
//                Article tmp = this.getArticle(rs.getInt(1));
//                if(!tmp.equals(null)){
//                    subList.add(tmp);
//                }
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return subList;
    
    
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
    