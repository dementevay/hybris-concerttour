# hybris-concerttour

E-Commerce project.
SAP Commerce (Hybris) version 1811.5
Based on B2B Accelerator

### Installation

1. Enviroments
    
    Need: Maven 3.5.X, jdk 8, Hybris  CXCOMM181100P_5-70004085
  
   - Install JDK 8, Maven
  
   - Add MAVEN_HOME to system environment variables.
    
2. Clone repository
    
     ```
    git clone https://github.com/dementevay/hybris-concerttour.git hybris
    ```
    
    _<HYBRIS_HOME_DIR\>_ /hybris to refer to the directory where you cloned the project.

3. Checkout
    
    - Navigate to the _<HYBRIS_HOME_DIR\>_
    
        ```
        cd hybris
        ```
        
    - Checkout on develop branch
    
        ```
        git checkout origin/feature/001
        ```
        
4. Скпировать всё из архива CXCOMM181100P_5-70004085 в _<HYBRIS_HOME_DIR\>_ кроме папки hybris

5. Руками скопировать всё из папки CXCOMM181100P_5-70004085/hybris/bin в _<HYBRIS_HOME_DIR\>_/hybris/bin

6. Скопировать папку 'data' в _<HYBRIS_HOME_DIR\>_/hybris

7. Сборка и запуск
   - Navigate to the _<HYBRIS_HOME_DIR\>_\hybris\bin\platform directory.
    
   - Set your ant environment by entering the following command:
    
      - On Windows:
        
                setantenv.bat
               
      - On Linux or Mac:
      
                . ./setantenv.sh  
            
   - ```ant clean all```
   - ```./hybrisserver.sh debug (Mac&Linux)```или ```hybrisserver.bat debug (Windows)```
        
        
 Open browser and go to https://localhost:9002/concerttours/concerts/

