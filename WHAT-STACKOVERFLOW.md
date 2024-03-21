# Stack, heap and stack overflow

Applications have two kinds of memory: **stack** and **heap**.

Both of these memory areas are in the RAM of the computer. There is typically 4-8 GB of RAM, nowadays also 16 GB being more and more common.

## Stack

Stack is fixed size, reserved for the app when it is loaded into memory. By default, Windows gives 1MB of stack memory to a process unless otherwise specified. That is the *default stack size*. Unixes (Linux and macOS included) have typically a default stack size of 8MB.

Small stack size is beneficial in a sense that if you have 20 processes running, they take on Windows 20 MB of memory for the stack. Same processes with Unix like OSes consume 160 MB of memory. If the processes could do with less than 8MB, that would be good -- more memory left for other use. Not all processes need that 8 MB anyways.

Java applications have 1 MB of stack memory by default in all operating systems.

On the other hand, large stack size is good in a sense that it gives more memory to the local variables of functions/methods and function calls. Local variables and the application's function call stack are kept in the stack memory. 

With small stack sizes, the apps can fail with **stack overflow** more easily. Stack overflow will terminate the app immediately. This can also happen if the application has been implemented in a way that uses lots of stack memory -- many and/or large local variables and deep, perhaps recursive function calls.

Stack overflow happens when the allocated stack memory runs out. This can happen if the programmer allocates too many small or large data structures from the stack:

```C
#include <stdio.h>

void recursive(int repeats);

int main() {
   recursive(20);
   return 0;
}

void recursive(int repeats) {
   printf("Repeats left: %d\n", repeats);
   long array[100000];

   if (--repeats >= 0) {
      recursive(repeats);
   } else {
      printf("At the bottom!\n");
   }
}
```

Here the main calls a recursive function that will call itself 20 times. 

Tässä pääohjelma kutsuu rekursiivista funktiota 20 kertaa. Ohjelman suorittaminen johtaa ohjelman kaatumiseen:

```console
./overflow
Repeats left: 20
Repeats left: 19
Repeats left: 18
Repeats left: 17
Repeats left: 16
Repeats left: 15
Repeats left: 14
Repeats left: 13
Repeats left: 12
Repeats left: 11
[1]    8500 segmentation fault  ./overflow
```

Since each recursive call consumes 100000 * 8 bytes of stack, totalling 800000 bytes. When the function calls itself 20 times, amount of the consumed stack would be already 16 000 000 bytes, that is 16MB -- the default stack size of the test machine 8 Mb has been consumed two times. So the executable crashes after the tenth recursive call when the stack memory is all reserved.

Very deep recursive function calls consume lots of stack, even though no large variables would be used. If you implement sorting with Quicksort (recursive sort algorithm), for example, and the sort would handle very very large dataset, that would lead to very deep function call stack. If all the stack memory would be used, a stack overflow would again crash the app.

For this reason, Python has a fixed limit of function call stack of 1000 entries. So if your recursive code goes deeper than 1000 calls, the app will fail. It is possible to increase this number, but then again the limits of the stack memory are waiting for the unsuspecting programmer...

So if stack is limited, what can you do about it? One good solution is to use heap memory.

## Heap

Heap is basically all the memory available in the computer, that the application may (try) to allocate. Usually heap allocation does not fail (often) with modern computers. If the RAM runs out, OSes can *swap* not so recently accessed memory consumed by apps and app data to disk to make more RAM available for currently running apps. This would slow down the computer when swapping happens, but usually users won't notice.

In embedded computers with no paging to disk and limited RAM sizes, RAM could actually run out. It is a good programming practice to make sure that your app can handle out of memory situations.

The array above would be allocated from the heap like this:

```C
#include <stdio.h>
#include <stdlib.h>

void recursive(int repeats);

int main() {
        recursive(20);
        return 0;
}

void recursive(int repeats) {
        printf("Repeats left: %d\n", repeats);
        long *array = malloc(sizeof(long) * 100000);

        if (--repeats >= 0) {
                recursive(repeats);
        } else {
                printf("At the bottom!\n");
        }
        free(array);
}
```

Now stack overflow does not happen, heap is enough for the data. And since the function call hierarchy does not get too deep, stack overflow does not happen for this reason either:

```console
./overflow
Repeats left: 20
Repeats left: 19
Repeats left: 18
Repeats left: 17
Repeats left: 16
Repeats left: 15
Repeats left: 14
Repeats left: 13
Repeats left: 12
Repeats left: 11
Repeats left: 10
Repeats left: 9
Repeats left: 8
Repeats left: 7
Repeats left: 6
Repeats left: 5
Repeats left: 4
Repeats left: 3
Repeats left: 2
Repeats left: 1
Repeats left: 0
At the bottom!
```

This example is using C. Using C++ or Java, you would use the `new` operator to allocate from the heap.

Here the pointer variable `array` is in the stack, but the actual huge memory area where the nubmers are stored is in the heap.

In languages such as C and C++, the programmer *must* take care to allocate *and* release the allocated heap. Otherwise the app is leaking memory, which is a very bad thing. In C, `free` function is  used. In C++, memory is freed by using the `delete` operator (or `delete []`, if an array is freed). 

Java and Swift are examples of languages which take care of releasing the allocated memory on behalf of the programmer. Java is using garbage collection to do this. Swift is using automatic reference counting.

So, the solution with large data elements is to use heap. What about recursive function calls? What if you have those? You cannot specify that call stack should be in heap.

This is the topic of the next chapter.

## Stack overflows caused by recursion in Java

If you implement recursive functions in your course project (e.g. for sorting) you will probably, with large data sets, have stack overflow exceptions.

Java allocates 1 MB of stack space for apps. This may not be enough when having deep recursive function calls.

Make sure that you do allocate the memory in heap instead of stack. After making sure, stack overflows still happen, then the culprit is the recursive calls.

If possible, the next thing to do is to implement sorting non-recursively. There are lots of different sorting algorithms that are not recursive. And even for quicksort, there exists non-recursive implementations, like Lampsort. Consider using those instead of recursion.

If you still want to use recursive functions, you can increase the default stack size of 1 MB Java provides to e.g. 4 or 8 MB.

The thing is that Java apps can be launched in different ways:

1. From the command line in terminal window.
2. From the IDE debugger using Run/Debug command.
3. From the IDE test framework.
4. From the command line test framework.

The stack size must be specified in *all* these paths to get the application running with the increased stack size.

### Launching Java app from command line

First the easy one, launching the app from the command line.

Provide JVM the stack size in the `java` command startup parameters:

```console
java -Xss8m -jar target/booksandwords-1.0-SNAPSHOT-jar-with-dependencies.jar
```

When running the app from the command line, start the app with `-Xss8m` to increase stack from default 1MB to 8MB.

Now the stack size should be ok. If not, increase the stack size. Or consider solutions using less stack.

### Launching Java apps from the Maven tests

To increase the stack size when launching the tests, you must add the `<configuration>` section below to the `pom.xml`, inside the already existing `plugin` section, to make sure `mvn test` succeed without stack overflow:

```XML
<plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-surefire-plugin</artifactId>
   <configuration>
      <argLine>-Xss8m</argLine>
   </configuration>
   <version>2.22.2</version>
</plugin>
```
Do this *carefully* so that you do not mess the whole `pom.xml` file.

### Launching Java apps or tests from the VS Code IDE

When running tests from VS Code to avoid stack overflow tests, do this. Add the configuration into your VS Code workspace settings under the section `java.test.config`:

```JSON
"-vmArgs" : ["-Xss8m"]
```

You can find the settings by selecting the cogwheel tool from bottom left corned of VSC and there select Settings... and then search for `"java.test.config"` to edit the java testing configuration to include the above `vmArgs` setting.

To fix things when you want to debug the BooksAndWords app from VSC and not throwing stack overflow, you need to  create a `launch.json` file where you also specify the increased stack size in the `vmArgs` setting:

```JSON
      {
         "type": "java",
         "name": "Launch BooksAndWords",
         "request": "launch",
         "mainClass": "oy.tol.tira.BooksAndWords",
         "projectName": "booksandwords",
         "vmArgs": "-Xss8m"
      }
```

Now, whatever way you launch the app, it is using the increased stack size. If the stack overflow was due to deep recursion, this should fix it.

# Final notes

Note that stack overflow can also happen because of a bug in your app. Increasing the stack size is not a correct path to take to fix bugs. Do this only if you know that deep recursive function calls that you *want* to execute, are the reason for the stack overflow.
