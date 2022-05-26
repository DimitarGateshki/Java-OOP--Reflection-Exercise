package BlackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner sc=new Scanner(System.in);
        Class<BlackBoxInt> clazz=BlackBoxInt.class;
        BlackBoxInt blackBoxInt;

        try {
            Constructor<BlackBoxInt> declaredConstructor = clazz.getDeclaredConstructor(int.class);
            declaredConstructor.setAccessible(true);
             blackBoxInt = declaredConstructor.newInstance(0);
        }catch (NoSuchMethodException
                | IllegalAccessException
                | InstantiationException
                | InvocationTargetException n){
            throw new IllegalStateException(n);
        }
        
        String input=sc.nextLine();
        while (!input.equals("END")){
            String[] tokens=input.split("_");
            String name=tokens[0];
            int numb=Integer.parseInt(tokens[1]);
            try {
                Method method = clazz.getDeclaredMethod(name, int.class);
                method.setAccessible(true);
                method.invoke(blackBoxInt,numb);
                Field field=clazz.getDeclaredField("innerValue");
                field.setAccessible(true);
                System.out.println(field.get(blackBoxInt));
            } catch (NoSuchMethodException
                    | InvocationTargetException
                    | IllegalAccessException
                    | NoSuchFieldException e) {
                e.printStackTrace();
            }

            input=sc.nextLine();
        }


    }
}
