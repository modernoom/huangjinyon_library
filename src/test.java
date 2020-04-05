import com.huangjinyong.library.Main;
import com.huangjinyong.library.controller.LoginController;
import com.huangjinyong.library.dao.AdminDao;
import com.huangjinyong.library.dao.impl.AdminDaoImpl;
import com.huangjinyong.library.entity.Admin;
import com.huangjinyong.library.entity.Comment;
import com.huangjinyong.library.entity.Student;
import com.huangjinyong.library.entity.User;
import com.huangjinyong.library.util.jdbchelper.core.ConditionSqlCreator;
import com.huangjinyong.library.util.jdbchelper.core.JdbcHelper;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;
import com.huangjinyong.library.util.jdbchelper.page.PageHelper;
import com.huangjinyong.library.util.jdbchelper.page.Support;
import com.huangjinyong.library.util.other.Check;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class test {
   AdminDao adminDao=new AdminDaoImpl();
   JdbcHelper jdbcHelper=new JdbcHelper();

   @Test
   public void test1(){
       Main.class.getClassLoader().getResourceAsStream("com/huangjinyong/library/view/StudentView.fxml");

   }

   @Test
   public void test2(){
      System.out.println(Check.checkUsername("aa"));
   }

   @Test
   public void test3(){
      List<Admin> byName = adminDao.findByName("aa", "aa");

      System.out.println(byName);
   }
   @Test
   public void test4(){

      List<Integer> list=new ArrayList();
      list.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
      PageHelper helper=new PageHelper();
      PageBean<Integer> pageBean = helper.doPage(1, 10, () -> list);
      System.out.println(pageBean.getCurrentSize());
      System.out.println(pageBean.getCurrentPage());
      System.out.println(pageBean.getTotalPage());
   }


   @Test
   public void test5(){
      Map map=new HashMap();
      map.put("id",1);

      String sql ="select * from student";
      String conditoinSql = ConditionSqlCreator.getConditionSql(sql, map);
      Object[] conditionVal = ConditionSqlCreator.getConditionVal(map);
      List<Student> query = jdbcHelper.query(conditoinSql, Student.class, conditionVal);
      System.out.println(query);
   }

   @Test
   public void test6(){
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try {
         System.out.println(simpleDateFormat.parse("2020-04-05 15:00:00"));
      } catch (ParseException e) {
         System.out.println("错啦");
      }


   }
}

