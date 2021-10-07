package json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import pojo.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class Json_T {

  /**
   * 利用gson  解析java数据 生成json数据
   */

  /**
   * gson.jar maven 配置文件
   *
   *     <dependency>
   *       <groupId>com.google.code.gson</groupId>
   *       <artifactId>gson</artifactId>
   *       <version>2.8.5</version>
   *     </dependency>
   */

  //json与JavaBean转化
  @Test
  public void T_JavaBean() {

    Person person = new Person(1, "wyl");

    //利用toJson方法将对象转化为Json字符串
    String toJson = new Gson().toJson(person);
    System.out.println(toJson);

    //利用fromJson方法将Json字符串转化为对象        注意：参数需要 利用反射获取转化该对象的实体类  XXX.class
    Person person_T = new Gson().fromJson(toJson, Person.class);
    System.out.println(person_T);

  }

  //json与List转化
  @Test
  public void T_List() {

    ArrayList<Person> personList = new ArrayList();
    personList.add(new Person(1, "wyl"));
    personList.add(new Person(2, "bsy"));

    //利用toJson方法将List集合转化为Json字符串
    String personList_String = new Gson().toJson(personList);
    System.out.println(personList_String);

    //利用fromJson方法将Json字符串转化为List集合     注意：参数需要   编写匿名内部类TypeToken<转化类型的泛型>   并且调用getType()方法
    ArrayList<Person> personList_T = new Gson().fromJson(personList_String,
      new TypeToken<ArrayList<Person>>() {
      }.getType());
    personList_T.forEach((person) -> System.out.println(person));

  }

  //json与Map的转化
  @Test
  public void T_Map() {

    HashMap<Integer, Person> personMap = new HashMap<>();
    personMap.put(1, new Person(1, "wyl"));
    personMap.put(2, new Person(2, "bsy"));

    //利用toJson方法将Map集合转化为Json字符串
    String personMap_String = new Gson().toJson(personMap);
    System.out.println(personMap_String);

    //利用fromJson方法将Json字符串转化为Map集合     注意：参数需要   编写匿名内部类TypeToken<转化类型的泛型>   并且调用getType()方法
    HashMap<Integer, Person> personMap_T = new Gson().fromJson(personMap_String,
      new TypeToken<HashMap<Integer, Person>>() {
      }.getType());
    personMap_T.forEach((k, person) -> System.out.println(k + " " + person));

  }


}
