package TestRefect.InjectPractice;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main{
  public static void main(String[] args) throws ClassNotFoundException {
    Class c = Class.forName(args[0]);
    print_class(c);
  }

  public static void print_class(Class c) {
    if (c.isInterface()) {
      System.out.print(Modifier.toString(c.getModifiers()) + " " + typename(c));
    } else if (c.getSuperclass() != null) {
      System.out.print(Modifier.toString(c.getModifiers()) + " class " + typename(c) + " extends "
          + typename(c.getSuperclass()));
    } else {
      System.out.print(Modifier.toString(c.getModifiers()) + " class " + typename(c));
    }

    Class[] interfaces = c.getInterfaces();
    if ((interfaces != null) && (interfaces.length > 0)) {
      if (c.isInterface())
        System.out.print(" extends ");
      else
        System.out.print(" implements ");
      for (int i = 0; i < interfaces.length; i++) {
        if (i > 0)
          System.out.print(", ");
        System.out.print(typename(interfaces[i]));
      }
    }

    System.out.println(" {"); 

    Constructor[] constructors = c.getDeclaredConstructors();
    for (int i = 0; i < constructors.length; i++)
      print_method_or_constructor(constructors[i]);

    Field[] fields = c.getDeclaredFields();
    for (int i = 0; i < fields.length; i++){
      print_field(fields[i]);
    }
    Method[] methods = c.getDeclaredMethods();
    for (int i = 0; i < methods.length; i++)
      print_method_or_constructor(methods[i]);
    System.out.println("}");
  }

  public static String typename(Class t) {
    String brackets = "";
    while (t.isArray()) {
      brackets += "[]";
      t = t.getComponentType();
    }
    String name = t.getName();
    int pos = name.lastIndexOf('.');
    if (pos != -1)
      name = name.substring(pos + 1);
    return name + brackets;
  }

  public static String modifiers(int m) {
    if (m == 0)
      return "";
    else
      return Modifier.toString(m) + " ";
  }

  public static void print_field(Field f) {
    System.out.println("  " + modifiers(f.getModifiers()) + typename(f.getType()) + " "
        + f.getName() + ";");
  }
  public static void print_method_or_constructor(Member member) {
    Class returntype = null, parameters[], exceptions[];
    if (member instanceof Method) {
      Method m = (Method) member;
      returntype = m.getReturnType();
      parameters = m.getParameterTypes();
      exceptions = m.getExceptionTypes();
      System.out.print("  " + modifiers(member.getModifiers()) + typename(returntype) + " "
          + member.getName() + "(");
    } else {
      Constructor c = (Constructor) member;
      parameters = c.getParameterTypes();
      exceptions = c.getExceptionTypes();
      System.out.print("  " + modifiers(member.getModifiers()) + typename(c.getDeclaringClass())
          + "(");
    }
    for (int i = 0; i < parameters.length; i++) {
      if (i > 0)
        System.out.print(", ");
      System.out.print(typename(parameters[i]));
    }
    System.out.print(")");
    if (exceptions.length > 0)
      System.out.print(" throws ");
    for (int i = 0; i < exceptions.length; i++) {
      if (i > 0)
        System.out.print(", ");
      System.out.print(typename(exceptions[i]));
    }
    System.out.println(";");
  }
}