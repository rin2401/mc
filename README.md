# Micro C Language - PPL Assignment
## Document
- Implement details in MC.pdf

## Install
- Java: 
    ```
    sudo apt install default-jdk
- Sbt: 
    ```
    curl -L http://dl.bintray.com/sbt/debian/sbt-1.2.6.deb
    sudo dpkg -i sbt-1.2.6.deb
    sudo apt-get update
    sudo apt-get install sbt
## Run
  - Access to sbt  
    ```
    sbt
  - Compile
    ```    
    compile
  - Test
    - All test
        ```
        test
    - Each test
        ```
        testOnly LexerSuite
        testOnly ParserSuite
        testOnly AstSuite
        testOnly CheckerSuite
        testOnly CodeGenSuite