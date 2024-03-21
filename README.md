# NJIT-DSA-2024
Repository for all exercise material for DSA in NJIT spring 2024

This repository contains the exercises and course projects of the Data Structures and Algorithms course. Everything is based on the Finnish implementation of the course (TIRA, Tietorakenteet ja algoritmit in Finnish), so please notify if you find somehting in Finnish.

You as a student should copy this repository by creating a fork into your own github account. You then clone that fork to your own personal computer (PC). You will then work on that forked private repository and use it do deliver your course work to the course teachers for evaluation.

How this is done, is explained in this and other instructions of this reposityry. In addition, the course material contain instructional videos regarding how this is done (some in Finnish, English versions will be uploaded as soon as they are available). The first exercise task of the course, `00-init`, is intended to test that all tools and your own repository is properly set up.

All students upload individual work.

**IMPORTANT note**: in the exercises and course projects, you are **not allowed** to use Java Collection classes nor algorithms (classes like `Collections`, `Arrays`, `ArrayList`, `LinkedList`, `Stack`, `Vector`, `Queue`, `Set`, any implementation of `Map`, `Arrays.sort`, `Collections.sort`, etc.) unless *explicitly* mentioned in a specific exercise/course project.

You **are allowed** to use `String` class as well as basic data types int, long, short, double, float, char, and plain arrays (e.g. String [] arrayOfStrings) and the "class" version of these (like `Integer`). When you are required to implement a **hash function**, you must **not** use ready made hash functions, e.g. `String.hashCode` but you must implement hash functions yourself using the basic data types and loops.

## Tools

The tools that are used in this course and you need to install, are listed in [TOOLS.md](TOOLS.md). 

**Install the tools first** and then continue reading forward.

## How to set up your workspace

The instructions in [SETUP.md](SETUP.md) instruct you how to get the course repository forked and cloned to your own PC. You will then do the exercises and course projects on your own PC. When you get things done, you will use git to

1. add changes and new code to your PC's local git repository,
1. push the code to your private git repository in GitLab,
1. from where the teachers can see your progress, help you out with problems, and finally clone your project to grade your work in the course. 

So, follow the instructions in  [SETUP.md](SETUP.md) -- you need to do the setup only *once* in the beginning of the course.

**IMPORTANT note**: in the exercises and course projects, you are **not allowed** to use Java Collection classes nor algorithms (classes like Collections, Arrays, ArrayList, LinkedList, Stack, Vector, Queue, Arrays.sort, Collections.sort, etc.) unless *explicitly* mentioned in a specific exercise/course project. Using String class as well as basic data types int, long, short, double, float, char, and plain arrays (e.g. String [] arrayOfStrings) is allowed.

## Timing and deadlines

The table below describes the timing of the course in Sring 2024. 

> Note that if you want and are able to, **you may proceed faster** than the schedule indicates.

Each subdirectory contains detailed instructions in `README-EN.md` files for that specific exercise or course project. As you move on to those exercises, view the readme files in each subdirectory for exercise / course project specific instructions.

When the course deadline comes, the teachers will check all student repositories for grading. The version on that date & time will be inpsected. Late deliveries are *not* taken into account.
The teacher will pull the students' source code files only, **not the test files**, and run the student's source codes aginast the original tests. So, **do not cheat by chaging tests**.

### Lectures

Online lectures over zoom as per schedule. Live lectures on site at NJIT during second and thrid weeks. The online lectures will be recorded.

In addition, there are short pre-recorded instructural videos available.

### Excercises

**Exercises** are scheduled so that you *start* working on them the week indicated. You may take a week or two to work on an exercise task depending on your load. Note the deadlines (DL) for the difference exercise tasks in the table below. But do not push the work too far ahead, then you have many unfinished exercises to work with, overloading you. It's a recipe for losing points. Rather start working on the exercises *before* the actual exercise sessions.

> Hint: You can keep track of your work by removing each `TODO` comment in the code for each exercise and project. When you cannot find any `TODO` items using the IDE search tool, and tests pass, you should be done. Note though that even though tests pass, your code may not be perfect or even acceptable if your implementation has faults not exposed by the tests.


| Week | Topic                   | Programming work                    | Notes / Deadlne   |
|------|-------------------------|-------------------------------------|-------------------|
|  01  | 00 Course intro         | [00-init](00-init)                  | Non-graded task   |
|      | 01 Topic intro          | [01-arrays](01-arrays)              | Until DL 1        |
|      | 02 Algorithm analysis   |                                     |                   |
|      | 03 Basic data strucutres|                                     |                   |
|  02  | 01 Intor recap          | [02-1-stack](02-1-stack)            | Until DL 1        |
|      | 02 Analysis recap       | [02-2-queue](02-2-queue)            | Until DL 1        |
|      | 03 Basic data st recap  | [03-binarysearch](03-binarysearch)  | Until DL 1        |
|      | 04 Sorting              |                                     |                   |
|  03  | 04 Sorting              | [04-phonebook](04-phonebook)         | Until DL 1        |
|      | 05 Hash tables          |                                     |                   |
|      | 06 Binsry search trees  |                                     |                   |
|  04  | 05 Hash tables          | Work on unfinished or next tasks    |                   |
|      | 06 Binary search trees  |                                     |                   |
|      | 07 Graphs BFS           |                                     | **DL 1: March 24**|
|  05  | 07 Graphs DFS           | [05-parenthesis-checker](05-parenthesis-checker)| Until DL 2        |
|      | 07 Graphs Kruskal       | [06-dailytasks](06-dailytasks)      | Until DL 2        |
|      | 07 Graphs Dijsktra      | [07-booksandwords](07-booksandwords)| Until DL 2        |
|      |                         |                                     |                   |
|  06  | 09 Independent study    | Independent work                    |                   |
|      |                         |                                     | **DL 2: April 7** |

Time of deadline is always 23.59.59 EET of the relevant day.
Note that 04-phoebook is a two-parts task. It cointains both hash table and binary search tree.
**Note that 07-books-and-words is a two-part task.** It contains both hash table and binary search tree. Completing both increases grade by 2, completing only one of them increases grade by 1.

## Grading

The course is graded with the following rules:

1. Exercise `00-init`does not affect grading. The purpose is to test that the tools are properly installed and to refresh your programming skills.
2. Teachers download your solutions, runs them against the original tests, and inspects the code visually to see that they are accepltabel at the latest after each deadline.The fact that test are passed, does not mean an acceptable or good solution (tests do not relveal everything). **Changes to the solutions after deadline are not considered**. If task is not accepted, you have one week to improve the solution.
3. All tasks until deadline 1 are required for a grade of 1 for the programming tasks.
4. Successfully completed tasks for deadline 2 increase the grade
5. All DL 1 tasks + 05 or 06 --> programming grade 2
6. All DL 1 tasks + both 05 and 06 --> programming grade 3.
7. All previous tasks + BST or hash table of 07 --> programminmg grade 4.
8. All previous tasks + both BST and hashtable of 07 --> programming grade 5.
9. Learning goals of the theory part is evaluated through Moodle Exams. Grade is determined by the exam score.
10. Final grade of the course is the average of theory grade and programming grade.

Remember that functionally correct solutions *are not enough*. The solutions must be implemented with *time complexity* in mind. If your solution is not *fast* enough with *large* data sets, it may be graded failed even though it may be functionally correct, i.e. produces the correct result. This is explained in detail in the course introductory lectures.

Also note that here you will learn how to implement data structures and algorithms to be used in also other contexts and by other developers. The code must not therefore do anything else than what is is supposed to do. Examples of what the code should *not include or do* are (what the submitted solutions should not contain:

* A data structure contains a `main()` method. (If you have created a main() method for own testing, remove it and make an own class (.java file) for your own tests in the same directory as the data structure classes, not test directory.)
* Code compiles with warnings.
* TODO comments created by the teacher. Remove these once the code is complete.
* Code commented out, left there for some reason. Remove these. Exceptions are if you want to leave an alternative solution to a problem **together with an explanation**.
* Unused classes, methods, variables, parmaeters, or constants. Remove these.
* Experimental, "trying this stuff out" kind of code, not part of the actual data structure or algorithm implementation. Remove these.
* Print out of something to the console (System.out.println or such), when not instructed to do so. *I/O is slow* and it slows down the execution of the code. This has effect especially if tests measure running time. If not required byt the task, remove extra prints.
* The data structure or algoritm should not use more memory than what is necessary to implement it. The rule of thumb is to implement the algorithm in place, like for example, Quicksort or Heap sort. Some algorithms are designed to use additinal memory, such as Merge sort, this is allowed.
* Unreadable or incomprehensible code. Use correct indentations, follow Java naming conventions, use meaningful names for variables and methods, so that the code is easy to understand. Use the IDE's formating tool (in VS Code: right mouse click in the code editor > Format Document). Classes start with capital letters (`FastHashTable`), methods, variablse, parameters with lower case letters following `smallCamelCase`naming style, constants, like `CAPITALIZED_CONSTANT`, should be entirely capitalized.

The structure of the code is important for the readability and thus understandability. If code is a mess, it is difficult to understand. From the book Clean Code by Robert C. Martin: 

> ”Back in my days working in the Bell Labs Software Production Research organization (Production, indeed!) we had some back-of-the-envelope findings that suggested that consistent indentation style was one of the most statistically significant indicators of low bug density.”

There are 5 different exams in the course. The exams are always at the end of the week (Friday) and will focus on the topics studied during that week. They may also contain questions from previous weeks. The exams are implemente as Moodle quizzes.
Theory grade from exams:

| Points | <29   | <34   | <40   | <47   | <53   | <=57   |
|--------|-------|-------|-------|-------|-------|--------|
| Grade  | Fail  |   1   |   2   |   3   |   4   |   5    |

The final grade is computed from the theory grade and the programming grade. Weight of theory grade in the final grade is 30% and the weight of the programming grade in the final grade is 70%.

All exams are mandatory. If you miss one ore more exmas, there will be one additional opportunity to do those exams at the end of the course.

## Copying and plagiarizing

All code delivered as course work must be written by the student him/herself taking part in the course. Copying code from others and from the internet in this course is **forbidden**. You may work together with a student friend or small group, but each of you must write **each and every line** of code to your project by yourself. You may do pair programming, for example, so that you work together on the problem to solve and *both of you* write your *own* code in your *own* repository to solve the tasks. *You yourself alone* are responsible for your code and receive the course grade from work done with your tasks.

You may use the course demonstrations and other course material for inspiration for your implementations in the exercises and course projects. You may -- and it is also recommended -- to search the internet for help. This is actually an important developer skill to have. But again, **each and every line of code** must be written by yourself, not copy-pasted.

**Remember referecing!** If you use any code from others as inspiration, you **must** acknowledge that in your code in the comments. Add the source of the inspiration in the comments and provide the URL to the inspiration.

All suspected cases of plagiarism will be reported to the study office.

## About

* Course material for Tietorakenteet ja algoritmit | Data structures and algorithms 2021-2022.
* Study Program for Information Processing Science, Department of Information Technology and Electrical Engineering, University of Oulu.
* Antti Juustila, INTERACT Research Group
* Adapted to NJIT:
* Pertti Karhapää, M3S Research Unit.
