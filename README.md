# CSC 207: Text Editor

**Author**: Alex Janeway

## Resources Used

+ _(TODO: fill me in)_
+ ...
+ I started using song lyrics for my tests during the gapBuffer tests so
  test veryLong was pulled from "I'm not a Vampire" by Falling in Reverse and
  test longInMiddle had lyrics from "Little Lion Man" by Mumford & Sons

## Analyzing the Simple String Buffer
The Insert Method:
    Relevant inputs: 
        char ch
    Critical Operations:
        this.buffer = this.buffer.concat(addition);
        this.buffer = addition.concat(this.buffer);
        String start = new String (this.buffer.substring(0, this.pos));
        String end = new String (this.buffer.substring(this.pos, this.buffer.length()));
        this.buffer = start.concat(addition);
        this.buffer = this.buffer.concat(end);
    Mathematical Model:
        Best option: T(n) = n
        Worst option: T(n) = 4n
        Overall: T(n) = 5n/2
    Big-O Charactization:
        insert is O(n)

The main operations that insert performs involve creating new Strings-
a process that, as they must copy over each individal element, has
an approximate runtime of n, where n is the number of characters within the
string. In the best casenario where one is adding an element at the front
or back of the string, only one new string  must be created, resulting in
a runtime of n. In the worst casenarios, where elements are added to the
middle of the string, four new strings must be created, two in the
process of separating the original string, and then two more strings are
created in the process of adding them back together, resulting in a 
runtime of 4n. This occurs because strings in Java are immutable, and because
of that, they cannot be updated by the programmer, and must be created from
Scratch. Because our best case is a runtime of n and our worst case
is a runtime of 4n, I took the average to get ~5n/2. This translates to
a Big-O characterization of n.

## Changelog

_(TODO: fill me in with a log of your committed changes)_
