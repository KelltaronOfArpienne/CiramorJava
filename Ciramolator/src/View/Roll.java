package View;

import java.util.HashMap;

public class Roll {
    private HashMap<Object, Integer> map;
  
  public Roll(){
    this.map = new HashMap<>();
  }
  
  public void add(Object o)
  {
    if (!this.map.containsKey(o))
        this.map.put(o, Integer.valueOf(0));
    this.map.put(o, Integer.valueOf(((Integer)this.map.get(o)).intValue() + 1));
  }
  
  public int getTimes(Abidados... abidados)
  {
    try
    {
      int times = -1;
      for (Abidados abidado : abidados)
      {
        int aux = ((Integer)this.map.get(Integer.valueOf(abidado.getVal()))).intValue() / abidado.times;
        if ((aux < times) || (times == -1))
          times = aux;
      }
      return times;
    }
    catch (Exception e) {}
    return 0;
  }
  
  public class Abidados{
    private int val;
    private int times;
    
    public Abidados(int val, int times){
      this.val = val;
      this.times = times;
    }
    
    public int getVal(){
      return this.val;
    }
    
    public void setVal(int val){
      this.val = val;
    }
    
    public int getTimes(){
      return this.times;
    }
    
    public void setTimes(int times){
      this.times = times;
    }
  }
  
  public Abidados newAbidados(int val, int times)
  {
    return new Abidados(val, times);
  }
}
