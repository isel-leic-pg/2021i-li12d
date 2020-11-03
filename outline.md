# Outline

## Week 1
### 07/10/2020 - Course introduction
* Syllabus, teaching methodology 
* [Evaluation](https://2021moodle.isel.pt/mod/page/view.php?id=121005) (for authenticated users)
* Bibliography
  * [Kotlin Apprentice (book)](https://www.amazon.com/Kotlin-Apprentice-Second-Beginning-Programming/dp/1950325008/ref=sr_1_1)
  * [Kotlin Home - language reference](https://kotlinlang.org/docs/reference/)

For reference:
* [Lecture video (in Portuguese)](https://www.youtube.com/watch?v=Nf6Q5UQ6lDk&list=PL8XxoCaL3dBgXjxTLx4Fm-uxTD8k-cXQA&index=1)

## Week 2
### 12/10/2020 - The programmer's universe (introduction)

> Underlying every programming language is a model
> of a computing system that its programs control.   

by John Backus, [here](https://dl.acm.org/doi/pdf/10.1145/359576.359579)

* Tecnhology stacks and programming models
  * [The tech stack drawn during the lecture](assets/tech_stack.png "A tech stack")

For reference:
* [Lecture video (in Portuguese)](https://www.youtube.com/watch?v=wEd6ARKXCjI&list=PL8XxoCaL3dBgXjxTLx4Fm-uxTD8k-cXQA&index=2)

### 14/10/2020 - Representations
* Our first steps in Kotlin
  * The `Hello world!` application
  * The REPL (_Read Evaluate Print Loop_)
  * [Kotlin playground](https://play.kotlinlang.org/)
  * Building and running a trivial program
    * Comand line tools (kotlinc, javac, java)
  
* Kotlin's type system, introduction
  * [Primitive types](https://kotlinlang.org/docs/reference/basic-types.html)
  * Values and expressions (and __composing__ expressions)
  * __Composing__ values through aggregate data types
    * [Data classes](https://kotlinlang.org/docs/reference/data-classes.html)
  
For reference:
* [Lecture video (in Portuguese)](https://www.youtube.com/watch?v=nobgBEA2oP8&list=PL8XxoCaL3dBgXjxTLx4Fm-uxTD8k-cXQA&index=3)

### 19/10/2020 - Representations, continued
* Kotlin's type system, continued
  * [Data classes](https://kotlinlang.org/docs/reference/data-classes.html)
    * Default construction parameters
    * Named arguments
    * [Parameters and arguments](https://en.wikipedia.org/wiki/Parameter_(computer_programming)#Parameters_and_arguments) (a.k.a. _formal_ parameters and _actual parameters_)
  * Strings, revisited
    * String templates and string concatenation
* Immutability
  * A simplified mental model for representations
  * The substitution model of evaluation

For reference:
* [Lecture video (in Portuguese)](https://www.youtube.com/watch?v=YELdO0TZEhM&list=PL8XxoCaL3dBgXjxTLx4Fm-uxTD8k-cXQA&index=4)

### 21/10/2020 - Setup
* Setting up the development environment

For reference:
* [Lecture video (in Portuguese)](https://www.youtube.com/watch?v=GZWstPijzxA&list=PL8XxoCaL3dBgXjxTLx4Fm-uxTD8k-cXQA&index=5)

### 26/10/2020 - Behaviour
* Elements of code reuse and code organization
  * Functions as
    * Pure functions
    * Effects
* Mental model for execution
  * The substitution model of evaluation
  * Program order vs execution order

For reference:
* [Lecture video (in Portuguese)](https://www.youtube.com/watch?v=1uH6NwxSHEo&list=PL8XxoCaL3dBgXjxTLx4Fm-uxTD8k-cXQA&index=6)
* [Code used to draw an explosion (a red circle) where the mouse is pressed](https://gist.github.com/palbp/d0f43caa85b21d8e91d09e03e9a0c5ab)

### 28/10/2020 - Putting it all together
* Representations and behaviour, coming together
  * Behaviour
    * Conditional expressions
      * `if else`
    * Functions, revisited
      * Named parameters
      * Default parameters
      * __Composing__ functions with functions (i.e. functions that call other functions)
    * Repetition, introduction
      * Expressing repetition using `canvas.setInterval`
* Representations
  * Primitive types, revisited
    * Implicit and explicit type conversions
  * Introduction to mutations 
    * `var` vs `val`
    * Imposing a discipline on mutations (for now, only admissible in the main function)
  
For reference:
* [Lecture video (in Portuguese)](https://www.youtube.com/watch?v=-S2WHawYf0c&list=PL8XxoCaL3dBgXjxTLx4Fm-uxTD8k-cXQA&index=7)
* [Code used to draw a growing explosion](https://gist.github.com/palbp/1d7bff47e83ce68e2c3f098c23b16092)

### 02/11/2020 - Coding session 
* Goal: Let's start to build our version of [Missile Command](https://en.wikipedia.org/wiki/Missile_Command)
* Using CanvasLib
  * [Documentation](https://github.com/CCISEL/CanvasLib/blob/master/docs/CanvasLib.pdf)
  * [Implementation on GitHub](https://github.com/CCISEL/CanvasLib)

For reference:
* [Lecture video (in Portuguese)](#02112020---coding-session)
* [Coding challenge solution](https://gist.github.com/palbp/6b3766d45f5cec89877ff12e0bd0da1e)
* [Gist of the Code written during the session](https://gist.github.com/palbp/94ab93113c69d12e44dcef9d99231b31)

### 04/11/2020 - Coding session (preview)
* Goal: Let's continue to build our version of [Missile Command](https://en.wikipedia.org/wiki/Missile_Command)
* Setting up our MissileCommand project
  * Importing code from GitHub
* Representations, continued
  * Representing colors through RGB encoding
  * An introduction to numerical bases
    * Binary and hexadecimal
* Behaviour, continued
  * Extension functions

For reference:
* [Lecture video (in Portuguese)](#02112020---coding-session)
