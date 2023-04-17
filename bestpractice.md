# Best practice

- ### Use javadoc for description your methods [[javadoc]](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html)

- ### Java
    - #### Use stream/map/list/set
    - #### Do not use System.out.*, use Console.*
    - #### Do not use string concatenation (+), use String.format or StringBuilder
    - #### Use component inside menu/menu item. Use controller inside component
    - #### Think about computational complexity.(e.g map.get() - O1, forEah/for/fori - On)
    - #### Tests

- ### Operators
    - #### if-else
        - do not use if else
        - use if with return instead of else
    - #### if ternary
        - use ternary if you need to return value instead if -> return
    - #### Switch
        - use switch instead of if where possible

- ### Class
    - #### One file - one class

- ### Enums
    - #### Create enums with constructor

- ### Use basic OOP principles
    - #### KISS - Keep It Stupid Simple
    - #### DRY - Do not Repeat Yourself
    - #### SLAP - Single Level of Abstraction Principle
    - #### SRP - Single Responsibility Principle
    - #### OCP - Open-Closed Principle
    - #### LSP - Liskov Substitution Principle
    - #### ISP - Interface Segregation Principle
    - #### DIP - Dependency Inversion Principle