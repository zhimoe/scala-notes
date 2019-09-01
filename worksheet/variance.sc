//协变和逆变
//java PECS (short for Producer extends and Consumer super)
//从集合的角度看如果你从中取出元素处理,那么这个集合是producer,
//如果你只是往集合中东西,那么这个集合是consumer
//假设你有个方法的参数是一个Thing的集合,你在方法中会遍历处理这个集合的元素那么
//Collection<? extends Thing> 因为这个参数是producer,应该面向子类扩展,任何Thing的子类集合也能带传递该方法
//实际上,你无法添加任何元素到Collection<? extends Thing>,因为你不知道这个参数的runtime type
//List<? extends Number> foo3 = new ArrayList<Number>();  // Number "extends" Number (in this context)
//List<? extends Number> foo3 = new ArrayList<Integer>(); // Integer extends Number
//List<? extends Number> foo3 = new ArrayList<Double>();  // Double extends Number

/*
"Producer Extends" - If you need a List to produce T values (you want to read Ts from the list), you need to declare it with ? extends T, e.g. List<? extends Integer>. But you cannot add to this list.

"Consumer Super" - If you need a List to consume T values (you want to write Ts into the list), you need to declare it with ? super T, e.g. List<? super Integer>. But there are no guarantees what type of object you may read from this list.

If you need to both read from and write to a list, you need to declare it exactly with no wildcards, e.g. List<Integer>

public class Collections {
  public static <T> void copy(List<? super T> dest, List<? extends T> src) {
      for (int i = 0; i < src.size(); i++)
        dest.set(i, src.get(i));
  }
}
 */

//List[_] is a type constructor,_ is for type parameter

//trait MyTrait[+T] {
//  private case class MyClass(c: T)
//}
class P {
  def toInt: Int = {
    12
  }
}

class Child extends P {
  override def toInt: Int = {
    10
  }
}


trait Function1[-A, +B] {
  def apply(a: A): B
}

val c: Function1[Child, Int] = (x: Child) => x.toInt
val p: Function1[P, Int] = (x: P) => x.toInt

//  val func1: Function1[P, Int] = c
val func2: Function1[Child, Int] = p


