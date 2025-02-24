# CSC 207: Text Editor

**Author**: Alex Janeway

## Resources Used

+ TTAP: Data Structures - Lab Manual (https://osera.cs.grinnell.edu/ttap/data-structures-labs/classical-encryption.html)
+ NetBeans
+ Java 17
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
commit e592144fbfbdf3addbe11240e89235829a7095c0 (HEAD -> main, origin/main, origin/HEAD)                              
Author: Janeway <janewaya@stibitz.cs.grinnell.edu>                                                                    
Date:   Sun Feb 23 23:22:05 2025 -0600                                                                                
                                                                                                                      
    added style changes and updated GapBuffer 

commit 5686de7b4b9679d6001bce6f3d721724d641f276 (HEAD -> main, origin/main, origin/HEAD)                              
Author: Janeway <janewaya@stibitz.cs.grinnell.edu>                                                                    
Date:   Sun Feb 23 22:16:06 2025 -0600                                                                                
                                                                                                                      
    Fully added file reading                                                                                          
                                                                                                                      
commit 6e65ec75a13191e3d8f70f208c21abfefe2a0545                                                                       
Author: Janeway <janewaya@stibitz.cs.grinnell.edu>                                                                    
Date:   Sun Feb 23 22:04:50 2025 -0600                                                                                
                                                                                                                      
    Added file reading and printing to the terminal   

commit 0b9c4ae8b83c9014b10ff4fdce61f4be5f9d0ffa                                                                       
Author: Janeway <janewaya@berkeley.cs.grinnell.edu>                                                                   
Date:   Thu Feb 20 17:57:51 2025 -0600                                                                                
                                                                                                                      
    Finished GapBuffer                                                                                                
                                                                                                                      
commit 7235f5820009531aa01351647d98850cecf6c652                                                                       
Author: Janeway <janewaya@berkeley.cs.grinnell.edu>                                                                   
Date:   Wed Feb 19 17:38:26 2025 -0600                                                                                
                                                                                                                      
    Part 1 done 
