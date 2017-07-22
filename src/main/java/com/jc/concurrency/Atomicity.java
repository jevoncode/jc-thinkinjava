package com.jc.concurrency;

/**
 * 反编译出来看结果，可以看出获取getfield和操作完后放回去putfield是分开的
 * 所以i++这些操作是非原子性的
 * javap -c Atomicity
 * @author jevoncode
 *
 */
public class Atomicity {
	int i;

	void f1() {
		i++;
	}

	void f2() {
		i += 3;
	}
}
/**
 * Compiled from "Atomicity.java"
public class com.jc.concurrency.Atomicity {
  int i;

  public com.jc.concurrency.Atomicity();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  void f1();
    Code:
       0: aload_0
       1: dup
       2: getfield      #2                  // Field i:I
       5: iconst_1
       6: iadd
       7: putfield      #2                  // Field i:I
      10: return

  void f2();
    Code:
       0: aload_0
       1: dup
       2: getfield      #2                  // Field i:I
       5: iconst_3
       6: iadd
       7: putfield      #2                  // Field i:I
      10: return
}

 */ 